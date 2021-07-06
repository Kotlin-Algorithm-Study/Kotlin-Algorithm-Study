package kr.loner.kotlinalgorithmstudy.loner


fun main() {
    println(멀쩡한_사각형(2,2))}

//https://programmers.co.kr/learn/courses/30/lessons/60057
private fun `문자열_압축`(text: String): Int {
//리스트 압축 카운트만큼 나온 스트링 리스트를 구하는 일급객체
    val toList: (Int) -> MutableList<String> = { pressCount ->
        val list = mutableListOf<String>()

        //step 으로 압축 카운트만큼 점프해서 압축카운트 갯수에 맞게 텍스트 전체를 분리함
        for (i in text.indices step pressCount) {

            //마지막으로 추가하는 인덱스인지 체크
            val checkLastIndex = (i + pressCount) - 1
            if (checkLastIndex < text.length - 1)
                list.add(text.slice(i..checkLastIndex))
            else
                list.add(text.slice(i until text.length))

        }
//        println(list.toString())
        /* output
        [a, s, d, a, s, d, a, s, d, a, s, d]
        [as, da, sd, as, da, sd]
        [asd, asd, asd, asd]
        [asda, sdas, dasd]
        [asdas, dasda, sd]
        [asdasd, asdasd]
        [asdasda, sdasd]
        [asdasdas, dasd]
        [asdasdasd, asd]
        [asdasdasda, sd]
        [asdasdasdas, d]
        [asdasdasdasd]
        */
        list
    }

    //분리한 List<String> 을 매개변수로 받아서 압축카운트에
    // 맞는 문자열을 만들고 최종 문자열의 사이즈를 리턴하는 일급객체
    val getStringDigit: (MutableList<String>) -> Int = {
        var result = ""
        var count = 1

        //맨마지막에 [i+1]를 비교해야되기 때문에 -1
        for (i in 0 until it.size - 1) {
            when {
                //현재 인덱스의 스트링과 다음 인덱스의 스트링이 같지 않다면 string 변수에 +=
                it[i] != it[i + 1] -> {
                    //최소 2개의 카운트 이상일때 다음과 이전이 다르다면
                    // 최종 문자열에 추가하고 1부터 다시 세기
                    // ex : 2asd
                    if (count != 1) {
                        result += "${count}${it[i]}"
                        count = 1
                    }
                    //count 1부터 맞지않는다면 바로 문자열 할당
                    else
                        result += it[i]

                }
                //현재와 다음이 맞다면 count ++
                it[i] == it[i + 1] -> count++
            }
        }

        // size-1로 마지막 인덱스까지 루프가 안돌기 때문에 last 인덱스 추가 할당
        result += if (count != 1)
            "${count}${it.last()}"
        else
            it.last()

        println(result)
        result.length
    }

    var answer = text.length
    for (pressCount in 1..text.length) {
        val getCount = getStringDigit(toList(pressCount))

        //answer 보다 getCount 가 적다면 answer 에 재할당
        if (getCount < answer) {
            answer = getCount
        }
    }
    return answer
}

//https://programmers.co.kr/learn/courses/30/lessons/42888
fun `오픈채팅방`(record: MutableList<String>):Array<String>{

    //유저의 정보만 표시할 Map
    val user = mutableMapOf<String, String>()
    return record
            .map {
                //공백기준으로 알림 id 닉네임 3가지를 분리시켜 리스트로 만듬
                val r = it.split(" ")
                //리스트의 첫번째 인덱스는 알림임
                val action = r.first()
                when (action) {
                    //user Map 에 key:유저id value: 유저네임 추가
                    "Enter", "Change" -> user += r[1] to r[2]
                }
                r
            }
            .asSequence()
            //Change 표시 없는것만 필터링
            .filter {
                //방 입/퇴장 컨트롤을 위해 Change 알림인 아이템은 잠시 제거
                it[0] != "Change" }
            .map {

                //유저 이름을 따로 추출
                val nickName = user[it[1]]
                //explanation 에서 행위만 추출
                val explanation = when (it[0]) {
                    "Enter" -> "님이 들어왔습니다."
                    "Leave" -> "님이 나갔습니다."
                    else -> throw IllegalArgumentException()
                }
                //추출한 2가지를 합침
                "$nickName$explanation"
            }
            //콜렉션 함수에 의해 모두 변형 후 리턴됨
            .toList().toTypedArray()
}

//https://programmers.co.kr/learn/courses/30/lessons/62048
//초 5 경시 대회 https://m.blog.naver.com/orbis1020/220664563768
fun `멀쩡한_사각형`(w: Int, h: Int): Long {
    fun gcd(a: Int, b: Int): Int = if (a % b == 0) b else gcd(b, a % b)
    return w.toLong() * h - (w.toLong() + h - gcd(w, h))
}



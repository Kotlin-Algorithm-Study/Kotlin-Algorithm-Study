package kr.loner.kotlinalgorithmstudy.ryan

import java.util.*

/**
 * KotlinAlgorithmStudy
 * Class: Day1
 * Created by pyg10.
 * Created On 2021-06-24.
 * Description:
 */

fun main(){

//   val compress = StringCompress()
//
//   println(compress.solution("aaaaaaaaaa"))
//
//    val chatRoom = OpenChatRoom()
//    chatRoom.solution(arrayOf("Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"))
//
//    val plain = PlainSquare()
//    println(plain.solution(3,3))
}

/**
 *
 * TestCase
 *
 * 1~1,000자리의 문자열
 * a -> 1
 * aabbaccc -> 7
 * ababcdcdababcdcd -> 9
 * abcabcdede -> 8
 * abcabcabcabcdededededede -> 14
 * xababcdcdababcdcd -> 17
 * a*1000 -> 5
 * a*100 -> 4
 * a*10 -> 3
 *
 */

class StringCompress{

    fun solution(s: String): Int {

        //압축된 문자열의 길이를 저장할 리스트
        val compress = mutableListOf(s.length) // 압축을 하지않은 상태 즉 기본상태도 추가

        for (i in 1 .. s.length/2){ // 문자열압축은 문자열 길이의 1/2만큼만 하면됨.
            val stack = Stack<String>() // 이전 스트링을 확인하기위한 스택
            val builder = StringBuilder() // 압축결과를 나타내는 스트링빌더
            var count = 1 // 중복된 스트링을 계산하기 위한 카운드
            var maxSize = 0 // 압축이 일어난 마지막 위치를 알기위한 사이즈

            for (j in 0 until s.length-i+1 step i){ // 문자열압축이 발생하는 범위(2개씩 자를경우 0,2 2,4 4,6) until을 사용하지않는다면 +1은 제외해도 무방
                val sliceString = s.slice(IntRange(j, j+i-1)) // 자르는 문자열

                //println("slice -> $i start -> $j end -> ${j+i-1} string -> $sliceString")
                if (stack.isEmpty()) stack.push(sliceString) // 이전값이 비어있다면 삽입(최초만 발생할 예정)
                else{
                    if (stack.peek() == sliceString) count++ // 이전값과 동일하다면 count ++
                    else{
                        if (count != 1) builder.append(count) // 이전값과 다르기때문에 count가 1이아닌경우는 추가
                        builder.append(stack.pop()) // 이전값을 추가
                        stack.push(sliceString) // 현재값을 스택에 삽입
                        count = 1 // 카운트 초기화
                    }
                }
                maxSize = j + i - 1 // 마지막으로 자른 위치
            }

            if(stack.isNotEmpty()){ // 스택이 비어있지않는경우(마지막)
                if (count != 1) builder.append(count) // count가 1이아닌경우는 추가
                builder.append(stack.pop()) // 마지막값 추가
            }

            if (s.length-1 > maxSize) builder.append(s.slice(IntRange(maxSize+1, s.length-1))) // 자르지않은 부분이있다면 마지막에 추가

            compress.add(builder.toString().length) // 압축된 문자열의 길이를 추가
        }

        compress.sort() // 길이가 작은순으로 정렬

        return compress.first() // 첫번째값을 리턴
    }

}

/**
 *
 * TestCase
 *
 * recode = 1~100,000
 * userId로 구분
 * Enter Leave Change 공백
 * ex) Enter [userid] [nickName]
 *
 * ["Enter uid123 Muzi"]
 *  -> ["Muzi님이 들어왔습니다."]
 *
 * ["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"]
 *  -> ["Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."]
 *
 */

class OpenChatRoom{
    fun solution(record: Array<String>): Array<String> {
        val answer = mutableListOf<String>()

        val userHashMap = hashMapOf<String, String>()

        record.forEach { // userId 기준 닉네임을 저장. 해쉬맵은 키가동일하면 값이변경되는것을 이용
            val splitList = it.split(" ")

            if (splitList[0] != "Leave")
                userHashMap[splitList[1]] = splitList[2]
        }

        record.forEach { // userId를 가지고 닉네임을 가져와서 출력해줌.
            val splitList = it.split(" ")

            when(splitList[0]){
                "Enter" -> answer.add("${userHashMap[splitList[1]]}님이 들어왔습니다.")
                "Leave" -> answer.add("${userHashMap[splitList[1]]}님이 나갔습니다.")
            }
        }

        return answer.toTypedArray()
    }
}


/**
 *
 * TestCase
 *
 * w, h 는 1억이하의 자연수
 *
 * w = 8 h = 12 -> 80
 * w = 1 h = 1 -> 0
 * w = 2 h = 2 -> 2
 * w = 3 h = 3 -> 6
 *
 * https://taesan94.tistory.com/55
 *
 * 1,2 차의경우에는 범위오류가 발생하여 다른값이 나오게 되어서 실패가 난걸로 추청
 *
 */

class PlainSquare{
    fun solution(w: Int, h: Int): Long {

        val broken = w + h - gcd(w, h)

        //1차
        //return ((w * h) - broken).toLong()

        //2차
        //return (w * h) - broken.toLong()

        //3차
        return w.toLong() * h.toLong() - broken
    }

    private fun gcd(x:Int, y:Int): Int = if (y != 0) gcd(y, x % y) else x
}
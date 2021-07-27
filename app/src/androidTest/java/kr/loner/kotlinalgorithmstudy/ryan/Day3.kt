package kr.loner.kotlinalgorithmstudy.ryan

import kotlin.math.ceil

/**
 * KotlinAlgorithmStudy
 * Class: Day3
 * Created by pyg10.
 * Created On 2021-07-01.
 * Description:
 */

fun main() {

    val menu = MenuRenewal()
    menu.solution(arrayOf("XYZ", "XWY", "WXA"), intArrayOf(2, 3, 4))

    val tuple = Tuple()
    tuple.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")

    val table = PredictionTable()
    table.solution(8, 4, 7)

}

/**
 *
 * TestCase
 *
 * orders = "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"
 * course = 2,3,4
 *
 * result = "AC", "ACDE", "BCFG", "CDE"
 *
 * orders = "ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"
 * course = 2,3,5
 *
 * result = "ACD", "AD", "ADE", "CD", "XYZ"
 *
 *
 * orders = "XYZ", "XWY", "WXA"
 * course = 2,3,4
 *
 * result = "WX", "XY"
 *
 */

class MenuRenewal {

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val menuHashmap = hashMapOf<List<Char>, Int>() // 코스요리, 주문횟수를 나타내는 해시맵

        orders.forEach { order ->
            (2..order.length).forEach { combineSize -> //  최소 2가지 이상의 단품메뉴로 구성되기때문에 2부터 시작
                val combined = mutableListOf<List<Char>>() // 조합 결과
                combination(combined, order.toList(), Array(order.length) { false }, 0, combineSize) // 조합 2 ~ orders 크기만큼
                combined.forEach { menu ->
                    menuHashmap += menu.sorted() to menuHashmap.getOrDefault(menu.sorted(), 0) + 1 // sort의 이유는 조합의 결과는 오름차순이 아니기때문에 해당 코스요리를 오름차순 정렬해줌. ex) 3번째 예제의 WX XW 가 다를수있기 때문
                }
            }
        }

        val answer = mutableListOf<String>()
        course.forEach { count ->
            val pairList = mutableListOf<Pair<List<Char>, Int>>() // 해시맵 결과
            menuHashmap.forEach { (t: List<Char>, u: Int) ->
                if (t.size == count && u >= 2) // 코스요리의 길이 = 단품메뉴 갯수 최소 2회의 주문이 들어온것만 뽑아냄
                    pairList.add(Pair(t, u))
            }
            if (pairList.isNotEmpty()) { // 비어있다면 해당 단품메뉴 갯수를 가진 경우는 없는것이기때문에 체크를 해주어야함.
                pairList.sortByDescending { it.second } // 주문횟수로 내림차순 정렬(가장 많이 주문한경우를 찾기위함)
                val standardCount = pairList[0].second // 주문횟수가 동일하다면 리뉴얼메뉴에 추가해야하기때문에 기준점으로 잡음
                val resultList = pairList.filter { it.second == standardCount } // 주문횟수가 가장 큰 경우와 동일한 경우를 필터링해줌.
                resultList.forEach { result ->
                    answer.add(result.first.joinToString("")) // 리스트 -> 스트링형태로 변환해서 리뉴얼 매뉴에 추가
                }
            }
        }

        return answer.sorted().toTypedArray() // 오름차순 정렬 후 배열로 변환
    }

    private fun <T> combination(
        answer: MutableList<List<T>>, // 조합결과
        el: List<T>, // 조합할 리스트 ex ) "ABCFG"
        ck: Array<Boolean>, // 원소 선택여부
        start: Int, // 시작위치
        target: Int // 조합의 원소 갯수
    ) {
        if (target == 0) {
            answer.addAll(listOf(el.filterIndexed { index, t -> ck[index] }))
        } else {
            for (i in start until el.size) {
                ck[i] = true
                combination(answer, el, ck, i + 1, target - 1)
                ck[i] = false
            }
        }
    }

}

/**
 *
 *  Test Case
 *
 *  s = {{2},{2,1},{2,1,3},{2,1,3,4}} -> [2,1,3,4]
 *  s = {{1,2,3},{2,1},{1,2,4,3},{2}} -> [2,1,3,4]
 *  s = {{20,111},{111}} -> [111,20]
 *  s = {{123}} -> [123]
 *  s = {{4,2,3},{3},{2,3,4,1},{2,3}} -> [3,2,4,1]
 */

class Tuple{
    fun solution(s: String): IntArray {

        val filteredTuple = s.substring(2, s.length - 2).split("},{") // {{},{}} 의 스트링에서 [[], [], []] 형태의 리스트로 변환

        val calculatorTuple = hashMapOf<Int, Int>() // 숫자, 몇번 나왔는지의 대한 카운트

        filteredTuple.forEach { it.split(",").forEach { s: String ->
            calculatorTuple[s.toInt()] = calculatorTuple.getOrDefault(s.toInt(), 0) + 1 //키가 존재한다면 +1 없다면 1
        } }

        val sortedTuple = calculatorTuple.toList().sortedWith(compareBy { it.second }).reversed().toMap() // value로 내림차순 정렬

        return sortedTuple.keys.toIntArray() // 정렬된 key들을 배열로 변환
    }
}

/**
 *
 * Test Case
 *  n = 8 A = 4 B = 7 -> 3
 *  n = 2 A = 1 B = 1 -> 1
 *
 */

class PredictionTable {
    fun solution(n: Int, a: Int, b: Int): Int {
        var answer = 0

        var aRank = a.toDouble() // A 올림을 이용하기위해서 Double로 변경
        var bRank = b.toDouble() // B 올림을 이용하기위해서 Double로 변경

        var table = n // 대진표

        while (table/2 != 0){ // 결승에서 만나는 경우도 포함 table 이 2인경우 결승
            aRank = ceil(aRank/2) // 1회반복할때마다 절반씩 5인 경우 다음경기엔 3이되어야하므로 올림을 이용하여 몇번재인지 설정
            bRank = ceil(bRank/2)

            answer++ // 대전횟수 카운트

            if (aRank == bRank) // 둘의 값이 동일한경우 대전을 한것이기때문에 탈출
                break

            table /=2 // 1회반복할때마다 반씩줄어듬
        }

        println(answer)
        return answer
    }
}



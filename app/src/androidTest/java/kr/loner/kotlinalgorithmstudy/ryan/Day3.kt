package kr.loner.kotlinalgorithmstudy.ryan

import java.util.*

/**
 * KotlinAlgorithmStudy
 * Class: Day3
 * Created by pyg10.
 * Created On 2021-07-01.
 * Description:
 */

fun main(){

//    val matrix = MatrixBorderRotation()
//    matrix.solution(3,3, arrayOf(intArrayOf(1,1,2,2), intArrayOf(1,2,2,3), intArrayOf(2,1,3,2),
//         intArrayOf(2,2,3,3)))
//
//    val bracket = CorrectBracket()
//    bracket.solution("[](){}")

    val search = RankSearch()
    search.solution(arrayOf("java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"),
    arrayOf("java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"))
}

/**
 *
 * x1 y1 x2 y2
 *
 * x1 y1 -> x1, y2 -> x2, y2 -> x2, y1 -> x1, y1
 * 1,1 -> 1,2 -> 2,2 -> 2,1 -> 1,1
 * 1,2 -> 1,3 -> 2,3 -> 2,2 -> 1,2
 *
 * 100 97 [1,1,100,97] -> [1]
 *
 */

class MatrixBorderRotation{

    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {

        val initArray = Array(rows) {row -> IntArray(columns) { i -> row*columns + i + 1 } } // 행렬 생성

        val minResult = mutableListOf<Int>()

        queries.forEach {
            var blankValue = 0
            val result = mutableListOf<Int>()

            for(i in it[1]..it[3]){// → 방향 회전
                if (i == it[1]){
                    blankValue = initArray[it[0]-1][i-1] //처음 회전방향은 첫 값이 비어있기때문에
                    result.add(blankValue)
                    continue
                }
                else{
                    val temp = blankValue
                    blankValue = initArray[it[0]-1][i-1]
                    result.add(blankValue)
                    initArray[it[0]-1][i-1] = temp
                }
            }

            for (i in it[0]..it[2]){// ↓ 방향 회전
                if (i == it[0])// 처음에는 이미 동일값을 가지고있기때문에 스킵
                    continue
                else{
                    val temp = blankValue
                    blankValue = initArray[i-1][it[3]-1]
                    result.add(blankValue)
                    initArray[i-1][it[3]-1] = temp
                }
            }

            for (i in it[3] downTo it[1]){// ← 방향 회전
                if (i == it[3])
                    continue
                else{
                    val temp = blankValue
                    blankValue = initArray[it[2]-1][i-1]
                    result.add(blankValue)
                    initArray[it[2]-1][i-1] = temp
                }
            }

            for (i in it[2] downTo it[0]){// ↑ 방향 회전
                if (i == it[2])
                    continue
                else{
                    val temp = blankValue
                    blankValue = initArray[i-1][it[1]-1]
                    result.add(blankValue)
                    initArray[i-1][it[1]-1] = temp
                }
            }

            if (!result.isNullOrEmpty()) {
                result.sort() // 오름차순 정렬
                minResult.add(result[0]) // 최소값 넣어줌.
            }
        }

        return minResult.toIntArray()
    }

}


class CorrectBracket{
    fun solution(s: String): Int {
        var answer: Int = 0

        for ( i in s.indices){ // 회전 몇개까지 시킬껀가
            val value = s.toMutableList() // 리스트로 변환
            for (j in 0 until i){ // 회전시킬 갯수
                value.add(value[0]) // 처음것을 맨마지막에 삽입
                value.removeAt(0) // 처음을 삽입해줬으니 제거
            }

            if (isCorrect(value)) // 옳은 괄호라면 카운트
                answer++
        }
        return answer
    }

    private fun isCorrect(bracket: MutableList<Char>): Boolean{
        val stack = Stack<Char>()

        for (i in bracket.indices){
            if (stack.isEmpty()) // 스택이 비어있다면 삽입
                stack.push(bracket[i])
            else{
                when(bracket[i]){ // 비어있지않다면 } ] ) 의 경우 스택의내용과 비교한후 삽입하거나 꺼냄
                    '}' -> if (stack.peek() == '{') stack.pop() else stack.push(bracket[i])
                    ']' -> if (stack.peek() == '[') stack.pop() else stack.push(bracket[i])
                    ')' -> if (stack.peek() == '(') stack.pop() else stack.push(bracket[i])
                    else -> stack.push(bracket[i]) // } ] ) 외의경우는 삽입
                }

            }
        }
        return stack.isEmpty() // 비어있다면 옳은괄호 비어있지않다면 옳지않은 괄호
    }
}


class RankSearch{

    fun solution(info: Array<String>, query: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()

        

        return answer
    }


    //1차 이진탐색 x 효율성 x 2차 점수 이진탐색후 필터 효율성 x
//    fun solution(info: Array<String>, query: Array<String>): IntArray {
//        val applicantList = mutableListOf<Applicant>()
//        val result = mutableListOf<Int>()
//
//        info.forEach { information->
//            val splitInfo = information.split(" ")
//            applicantList.add(Applicant(splitInfo[0], splitInfo[1], splitInfo[2], splitInfo[3], splitInfo[4]))
//        }
//  
//        val sortedList = applicantList.sortedBy { it.score.toInt() }
//
//        query.forEach {eachQuery->
//            val splitQuery = eachQuery.replace("and ", "").split(" ")
//            val startIndex = binarySearch(sortedList, splitQuery[4].toInt())
//            println("score -> ${splitQuery[4]} slice -> ${sortedList.slice(IntRange(startIndex, sortedList.size-1))}")
//            result.add(sortedList.slice(IntRange(startIndex, sortedList.size-1)).filter { (splitQuery[0] == "-" || it.language == splitQuery[0]) && (splitQuery[1] == "-" || it.job == splitQuery[1]) && (splitQuery[2] == "-" || it.career == splitQuery[2]) && (splitQuery[3] == "-" || it.soulFood == splitQuery[3])}.size)
//        }
//
//        println(result)
//
//        return result.toIntArray()
//    }
//
//    private fun binarySearch(item: List<Applicant>, score: Int): Int{
//        var low = 0
//        var high = item.size
//        while (low < high){
//            val mid = low + (high - low) / 2
//
//            when{
//                item[mid].score.toInt() < score -> low = mid + 1
//                else -> high = mid
//            }
//        }
//
//        return low
//    }
//
//    data class Applicant(val language: String, val job: String, val career: String, val soulFood: String, val score: String)
}
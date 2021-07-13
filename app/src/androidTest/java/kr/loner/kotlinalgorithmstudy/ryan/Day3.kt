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
 *  Test Case
 *
 *  s = {{2},{2,1},{2,1,3},{2,1,3,4}} -> [2,1,3,4]
 *  s = {{1,2,3},{2,1},{1,2,4,3},{2}} -> [2,1,3,4]
 *  s = {{20,111},{111}} -> [111,20]
 *  s = {{123}} -> [123]
 *  s = {{4,2,3},{3},{2,3,4,1},{2,3}} -> [3,2,4,1]
 */

//class Tuple{
//    fun solution(s: String): IntArray {
//
//        val filteredTuple = s.substring(2, s.length - 2).split("},{") // {{},{}} 의 스트링에서 [[], [], []] 형태의 리스트로 변환
//
//        val calculatorTuple = hashMapOf<Int, Int>() // 숫자, 몇번 나왔는지의 대한 카운트
//
//        filteredTuple.forEach { it.split(",").forEach { s: String ->
//            calculatorTuple[s.toInt()] = calculatorTuple.getOrDefault(s.toInt(), 0) + 1 //키가 존재한다면 +1 없다면 1
//        } }
//
//        val sortedTuple = calculatorTuple.toList().sortedWith(compareBy { it.second }).reversed().toMap() // value로 내림차순 정렬
//
//        return sortedTuple.keys.toIntArray() // 정렬된 key들을 배열로 변환
//    }
//}

/**
 *
 * Test Case
 *  n = 8 A = 4 B = 7 -> 3
 *  n = 2 A = 1 B = 1 -> 1
 *
 */

//class PredictionTable {
//    fun solution(n: Int, a: Int, b: Int): Int {
//        var answer = 0
//
//        var aRank = a.toDouble() // A 올림을 이용하기위해서 Double로 변경
//        var bRank = b.toDouble() // B 올림을 이용하기위해서 Double로 변경
//
//        var table = n // 대진표
//
//        while (table/2 != 0){ // 결승에서 만나는 경우도 포함 table 이 2인경우 결승
//            aRank = ceil(aRank/2) // 1회반복할때마다 절반씩 5인 경우 다음경기엔 3이되어야하므로 올림을 이용하여 몇번재인지 설정
//            bRank = ceil(bRank/2)
//
//            answer++ // 대전횟수 카운트
//
//            if (aRank == bRank) // 둘의 값이 동일한경우 대전을 한것이기때문에 탈출
//                break
//
//            table /=2 // 1회반복할때마다 반씩줄어듬
//        }
//
//        println(answer)
//        return answer
//    }
//}


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
package kr.loner.kotlinalgorithmstudy.ryan

import java.util.*
import kotlin.math.ceil

/**
 * KotlinAlgorithmStudy
 * Class: Day2
 * Created by pyg10.
 * Created On 2021-06-29.
 * Description:
 */

fun main() {

//    val table = PredictionTable()
//    table.solution(2, 1, 1)

//    val tuple = Tuple()
//    tuple.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")

    val max = MaxCalculator()
    println("result -> ${max.solution("200-300-500-600*40+500+500")}")

}

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
 * expression = 100-200*300-500+20 -> 60420
 * expression = 50*6-3*2 -> 300
 * expression = 200-300-500-600*40+500+500 -> 1248000
 *
 */

class MaxCalculator {
    fun solution(expression: String): Long {
        var answer: Long = 0

        val priorityOperator = arrayOf("*+-", "*-+", "+*-", "+-*", "-*+", "-+*") // 우선순위

        val builder = StringBuilder() // 자르기전 문자열을 나타내기위함.

        val calculator = Stack<String>() // 하나씩 넣으면서 계산을 할 생각이기때문에 스택을 이용 바로앞의 내용을 pop으로 꺼내올수있기때문

        val result = mutableListOf<Long>() // 계산결과를 담을 리스트

        expression.forEach { //문자열 자르기좋게 * + - 앞뒤에 공백추가
            when (it) {
                '*' -> builder.append(" * ")
                '-' -> builder.append(" - ")
                '+' -> builder.append(" + ")
                else -> builder.append(it.toString())
            }
        }


        priorityOperator.forEach { operator ->
            val beforeExpression = builder.toString().split(" ").toMutableList() // 공백을 기준으로 문자열을 자름. 수정이 필요하기때문에 mutableList로 변환
            var isCalculator = false // 직전에 계산을 하였는지 설정 자바의경우 for문 안의 변수를 수정할수있지만(ex) for(int i =0; i<5; i++) i = i+1) 코틀린의 경우에는 불가능하기때문에 이를 핸들링하기위한 변수
            operator.forEach { singleOperator ->

                for (i in beforeExpression.indices){

                    if (isCalculator) { //계산을 한적이 있다면
                        isCalculator = !isCalculator
                        continue // 이번꺼는 넘기고 다음부터
                    }else{
                        if (beforeExpression[i] == singleOperator.toString()){ // 연산자가나왔다면
                            val beforeValue = calculator.pop() //이전값을 꺼낸후
                            when(singleOperator){
                                '*' ->{
                                    calculator.push((beforeValue.toLong() * beforeExpression[i + 1].toLong()).toString()) // 계산하여 다시 삽입
                                    isCalculator = !isCalculator // 계산을 하였으니 반대값으로 설정
                                }
                                '-' ->{
                                    calculator.push((beforeValue.toLong() - beforeExpression[i + 1].toLong()).toString())
                                    isCalculator = !isCalculator
                                }
                                '+' ->{
                                    calculator.push((beforeValue.toLong() + beforeExpression[i + 1].toLong()).toString())
                                    isCalculator = !isCalculator
                                }
                            }
                        }else calculator.push(beforeExpression[i]) // 연산자와 동일하지않다면 스택에 삽입
                    }
                    println("all -> $operator single -> $singleOperator $calculator")
                }

                beforeExpression.clear() // 1회 반복할때마다 계산한 결과값들이 들어가야하기때문에 먼저 리스트 초기화
                val calculatorSize = calculator.size // 스택의 사이즈가 변경되기때문에 밖에서 변수로 설정
                for (i in 0 until calculatorSize){
                    beforeExpression.add(calculator.pop()) // 스택의 내용을 넣음
                }
                beforeExpression.reverse() // 스택은 선입후출이기때문에 뒤의내용이 먼저나오게됨. 따라서 뒤집어주어야 정상적

                println(beforeExpression)
            }
            result.add(beforeExpression[0].removePrefix("-").toLong()) // 마지막에는 한개만 가지고있는 리스트가 반환되기에 -인경우 삭제해주고 결과리스트에 삽입
        }
        result.sortDescending() // 내림차순으로 변경
        return result[0] // 내림차순이기에 처음값이 가장 큼.
    }
}
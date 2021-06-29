package kr.loner.kotlinalgorithmstudy

import android.util.Log
import kotlin.math.abs
import kotlin.math.ceil

/**
 * KotlinAlgorithmStudy
 * Class: Day2
 * Created by pyg10.
 * Created On 2021-06-29.
 * Description:
 */

fun main(){

    val table = PredictionTable()
    table.solution(2, 1, 1)

}

/**
 *
 * Test Cast
 *  n = 8 A = 4 B = 7 -> 3
 *  n = 2 A = 1 B = 1 -> 1
 *
 */

class PredictionTable {
    fun solution(n: Int, a: Int, b: Int): Int {
        var answer = 0

        var aRank = a.toDouble() // A
        var bRank = b.toDouble() // B

        var table = n // 대진표

        while (table/2 != 0){ // 결승에서 만나는 경우도 포함 table 이 2인경우 결승
            aRank = ceil(aRank/2) // 1회반복할때마다 절반씩 5인 경우 다음경기엔 3이되어야하므로 올림을 이용하여 몇번재인지 설정
            bRank = ceil(bRank/2)

            answer++ // 대전횟수 카운트

            if (aRank == bRank)
                break

            table /=2 // 1회반복할때마다 반씩줄어듬
        }

        println(answer)
        return answer
    }
}
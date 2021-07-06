package kr.loner.kotlinalgorithmstudy.ryan

/**
 * KotlinAlgorithmStudy
 * Class: Day4
 * Created by pyg10.
 * Created On 2021-07-05.
 * Description:
 */

fun main(){

}

class CandidateKey{

    fun solution(relation: Array<Array<String>>): Int {
        var answer = 0

        val setKey = mutableListOf<Key>()
        relation.forEach { setKey.add(Key(it[0], it[1], it[2], it[3])) }

        if (setKey.map { it.studentID }.toSet().size == setKey.size) answer++


        return answer
    }

    data class Key(val studentID: String, val name: String, val major: String, val grade: String)

}
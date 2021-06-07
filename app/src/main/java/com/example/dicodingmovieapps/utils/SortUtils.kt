package com.example.dicodingmovieapps.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val DEFAULT = "Default"
    const val HIGHEST = "Highest"
    const val Lowest = "Lowest"

    fun getSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM moviesEntities ")
        if (filter == HIGHEST) {
            simpleQuery.append("ORDER BY moviesScore DESC")
        } else if (filter == Lowest) {
            simpleQuery.append("ORDER BY moviesScore ASC")
        } else if (filter == DEFAULT) {
            simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryTv(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tvEntities ")
        if (filter == HIGHEST) {
            simpleQuery.append("ORDER BY tvScore DESC")
        } else if (filter == Lowest) {
            simpleQuery.append("ORDER BY tvScore ASC")
        } else if (filter == DEFAULT) {
            simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}
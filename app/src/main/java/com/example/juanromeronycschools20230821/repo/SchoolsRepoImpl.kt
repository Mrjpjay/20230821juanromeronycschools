package com.example.juanromeronycschools20230821.repo

import com.example.juanromeronycschools20230821.api.SchoolData
import com.example.juanromeronycschools20230821.api.SchoolDetailData
import com.example.juanromeronycschools20230821.api.SchoolsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SchoolsRepoImpl : SchoolRepository {

    override suspend fun getSchools(listener: SchoolsListener): Unit = withContext(Dispatchers.IO) {
        try {
            val call = SchoolsService().instance.getSchools().execute()
            if (call.isSuccessful) {
                val list = mutableListOf<SchoolData>()
                call.body()?.forEach { school ->
                    list.add(
                        SchoolData(
                            dbn = school.dbn,
                            schoolName = school.schoolName,
                            neighborhood = school.neighborhood,
                            location = school.location,
                            website = school.website
                        )
                    )
                }
                listener.onSuccess(list)
            } else {
                listener.onError("${call.body()}")
            }
        } catch (e: Exception) {
            listener.onError("$e")
        }
    }

    override suspend fun getSchoolDet(dbn: String, listener: SchoolDetailListener): Unit = withContext(Dispatchers.IO) {
        try {
            val call = SchoolsService().instance.getSchool(dbn).execute()
            if(call.isSuccessful){
                if(call.body()?.isEmpty() == true){
                    listener.empty(true)
                    return@withContext
                }
                call.body()?.forEach { school ->
                    listener.onDetails(
                        SchoolDetailData(
                            dbn = school.dbn,
                            schoolName = school.schoolName,
                            numOfSatTestTakers = school.numOfSatTestTakers,
                            satCriticalReadingAvgScore = school.satCriticalReadingAvgScore,
                            satMathAvgScore = school.satMathAvgScore,
                            satWritingAvgScore = school.satWritingAvgScore
                        )
                    )
                }
            } else {
                listener.onError("${call.body()}")
            }
        }catch (e: Exception){
            listener.onError("$e")
        }
    }

    interface SchoolDetailListener{
        fun onDetails(detail: SchoolDetailData)
        fun onError(e: String)
        fun empty(b: Boolean)
    }
    interface SchoolsListener{
        fun onSuccess(list: MutableList<SchoolData>)
        fun onError(e: String)
    }
}
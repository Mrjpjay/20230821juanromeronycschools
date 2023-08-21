package com.example.juanromeronycschools20230821.repo

interface SchoolRepository {
    suspend fun getSchools(listener: SchoolsRepoImpl.SchoolsListener)
    suspend fun getSchoolDet(dbn: String, listener: SchoolsRepoImpl.SchoolDetailListener)
}
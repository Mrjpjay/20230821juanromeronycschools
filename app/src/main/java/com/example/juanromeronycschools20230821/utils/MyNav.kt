package com.example.juanromeronycschools20230821.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.juanromeronycschools20230821.view.DetailCompose
import com.example.juanromeronycschools20230821.view.MainCompose
import com.example.juanromeronycschools20230821.viewmodel.SchoolDetViewModel
import com.example.juanromeronycschools20230821.viewmodel.SchoolsViewModel

@Composable
fun MyNav(){
    val navController = rememberNavController()
    val viewModel = viewModel<SchoolsViewModel>()
    val schoolDetVM = viewModel<SchoolDetViewModel>()


    NavHost(navController = navController, startDestination = "MainCompose"){
        composable("MainCompose"){ MainCompose(navController,viewModel)}
        composable("DetailCompose"){ DetailCompose(viewModel,schoolDetVM) }
    }
}
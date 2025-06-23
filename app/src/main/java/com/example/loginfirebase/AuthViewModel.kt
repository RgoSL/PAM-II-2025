package com.example.loginfirebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value = AuthState.Unauthenticated
        }else{
            _authState.value = AuthState.Authenticated
        }
    }

    fun  login(email : String, senha :  String){

        if (email.isEmpty() || senha.isEmpty()){
            _authState.value = AuthState.Error("Email ou senha não pode estar vazio!")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener{task->
                if (task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?: "Algo não funcionou.")
                }
            }
    }

    fun signup(email: String, senha : String) {

        if (email.isEmpty() || senha.isEmpty()){
            _authState.value = AuthState.Error("Email ou senha não podem estar vazios")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener({task ->
                if (task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Algo não funcionou")
                }

            })
    }

    fun signout(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object  Loading : AuthState()
    data class Error(val message: String) : AuthState()
}
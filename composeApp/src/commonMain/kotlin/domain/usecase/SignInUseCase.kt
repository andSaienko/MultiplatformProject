package domain.usecase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuthException
import dev.gitlive.firebase.auth.auth

class SignInUseCase(
    firebase: Firebase
) {
    private val firebaseAuth = firebase.auth

    suspend operator fun invoke(email: String, password: String) {
        try {
            firebaseAuth.signInWithEmailAndPassword(
                email = email,
                password = password
            )
        } catch (e: FirebaseAuthException) {
            println(e.message)
        }
    }
}
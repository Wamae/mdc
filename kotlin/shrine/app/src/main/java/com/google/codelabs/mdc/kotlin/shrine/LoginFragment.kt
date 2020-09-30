package com.google.codelabs.mdc.kotlin.shrine

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


/**
 * Fragment representing the login screen for Shrine.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.shr_login_fragment, container, false)
        val passwordTextInput: TextInputLayout = view.findViewById(R.id.password_text_input)
        val passwordEditText: TextInputEditText = view.findViewById(R.id.password_edit_text)
        val nextButton: MaterialButton = view.findViewById(R.id.next_button)

        // Set an error if the password is less than 8 characters.
        nextButton.setOnClickListener {
            if (!isPasswordValid(passwordEditText.text)) {
                passwordTextInput.error = getString(R.string.shr_error_password)
            } else {
                passwordTextInput.error = null // Clear the error
                (activity as NavigationHost?)!!.navigateTo(ProductGridFragment(), false) // Navigate to the next Fragment
            }
        }

        // Clear the error once more than 8 characters are typed.
        passwordEditText.setOnKeyListener { view, i, keyEvent ->
            if (isPasswordValid(passwordEditText.text)) {
                passwordTextInput.error = null //Clear the error
            }
            false
        }
        return view
    }


    // "isPasswordValid" from "Navigate to the next Fragment" section method goes
    open fun isPasswordValid(@Nullable text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}

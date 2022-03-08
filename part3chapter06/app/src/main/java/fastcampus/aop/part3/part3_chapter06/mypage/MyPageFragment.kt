package fastcampus.aop.part3.part3_chapter06.mypage

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fastcampus.aop.part3.part3_chapter06.R
import fastcampus.aop.part3.part3_chapter06.databinding.FragmentMypageBinding

class MyPageFragment: Fragment(R.layout.fragment_mypage) {

    private var binding: FragmentMypageBinding? = null
    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentMypageBinding = FragmentMypageBinding.bind(view)
        binding = fragmentMypageBinding

        fragmentMypageBinding.singInOutButton.setOnClickListener {
            binding?.let { binding ->
                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                if (auth.currentUser == null) {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity()) { task ->
                            if (task.isSuccessful) {
                                successSignIn()
                            } else {
                                Toast.makeText(
                                    context,
                                    "로그인에 실패했습니다. 이메일 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                            }
                        }

                } else {
                    auth.signOut()
                    binding.emailEditText.text.clear()
                    binding.passwordEditText.text.clear()
                    binding.emailEditText.isEnabled = true
                    binding.passwordEditText.isEnabled = true

                    binding.singInOutButton.text = "로그인"
                    binding.singInOutButton.isEnabled = false
                    binding.singUpButton.isEnabled = false
                }

            }
        }

        fragmentMypageBinding.singUpButton.setOnClickListener {
            binding?.let { binding ->
                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if(task.isSuccessful) {
                            Toast.makeText(context,"회원가입에 성공했습니다. 로그인 버튼을 눌러주세요.", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context,"회원가입에 실패했습니다. 이미 가입한 이메일일 수 있습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }

            }
        }

        fragmentMypageBinding.emailEditText.addTextChangedListener {
            binding?.let { binding ->
                val enable =
                    binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()
                binding.singInOutButton.isEnabled = enable
                binding.singUpButton.isEnabled = enable
            }
        }

        fragmentMypageBinding.passwordEditText.addTextChangedListener {
            binding?.let { binding ->
                val enable =
                    binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()
                binding.singInOutButton.isEnabled = enable
                binding.singUpButton.isEnabled = enable
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if(auth.currentUser == null) {
            binding?.let { binding ->
                binding.emailEditText.text.clear()
                binding.passwordEditText.text.clear()
                binding.emailEditText.isEnabled = true
                binding.passwordEditText.isEnabled = true
                binding.singInOutButton.text = "로그인"
                binding.singInOutButton.isEnabled = false
                binding.singUpButton.isEnabled = false
            }
        } else {
            binding?.let { binding ->
                binding.emailEditText.setText(auth.currentUser!!.email)
                binding.passwordEditText.setText("*********")
                binding.emailEditText.isEnabled = false
                binding.passwordEditText.isEnabled = false
                binding.singInOutButton.text = "로그아웃"
                binding.singInOutButton.isEnabled = true
                binding.singUpButton.isEnabled = true
            }
        }
    }

    private fun successSignIn() {
        if(auth.currentUser == null) {
            Toast.makeText(context, "로그인에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        binding?.emailEditText?.isEnabled = false
        binding?.passwordEditText?.isEnabled = false
        binding?.singUpButton?.isEnabled = false
        binding?.singInOutButton?.text = "로그아웃"
    }
}
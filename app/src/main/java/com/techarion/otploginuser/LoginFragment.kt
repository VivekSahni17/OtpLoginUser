package com.techarion.otploginuser


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.techarion.otploginuser.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var mBinding:FragmentLoginBinding

    @Inject
    lateinit var tokenManager: TokenManager
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        mBinding = FragmentLoginBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mBinding.btnsend.setOnClickListener {
            val request = getUserPhoneNumber()
            mainViewModel.sendOtp(request)
        }
        bindObservers()
    }




    private fun getUserPhoneNumber(): User { Log.d("number",mBinding.etText.toString())
//        val no: String =
//            mBinding.etText.text.toString() //this will get a string
//
//        val no2 = no.toInt()

        //val jsonobject: JSONObject = jsonarray.getJSONObject(0)


       // val obj = JSONObject(content)
        return User(mBinding.etText.toString())

    }


    private fun bindObservers() {
        mainViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            // mBinding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    //tokenManager.saveToken(it.data!!.ph)
                   // findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
                    Toast.makeText(activity,"Success", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Error -> {
                    //showValidationErrors(it.message.toString())
                }
                is NetworkResult.Loading ->{
                    //binding.progressBar.isVisible = true
                }
            }
        })
    }


}
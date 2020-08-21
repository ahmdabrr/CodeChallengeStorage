package com.example.codechallengestorage.Memo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.codechallengestorage.UserLogin.ProfileUser
import com.example.codechallengestorage.R
import kotlinx.android.synthetic.main.fragment_add_memo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentAddMemo.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentAddMemo : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var db: DbMemo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_memo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DbMemo.getInstance(context!!)?.let {
            db = it
        }


        tv_tambah.setOnClickListener {
            val memo = Memo(
                id = null,
                tanggal = et_add_tanggal.text.toString(),
                isimemo = et_add_memo.text.toString()
            )

            GlobalScope.launch {
                val memoAdded = db.memoDao().addMemo(memo)
            }
            (activity as ProfileUser).fetchData()
            dismiss()
        }

        tv_cancel.setOnClickListener {
            dismiss()
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentAddMemo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): FragmentAddMemo {
            return FragmentAddMemo()
        }
//        fun newInstance(param1: String, param2: String) =
//            FragmentAddMemo().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}
package com.example.codechallengestorage.Memo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.codechallengestorage.UserLogin.ProfileUser
import com.example.codechallengestorage.R
import kotlinx.android.synthetic.main.fragment_edit_memo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentEditMemo.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentEditMemo : DialogFragment() {
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
        return inflater.inflate(R.layout.fragment_edit_memo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        et_edit_tanggal.setText(tanggal)
        et_edit_memo.setText(isiMemo)

        DbMemo.getInstance(context!!)?.let {
            db = it
        }

        tv_update.setOnClickListener {
//            val df = SimpleDateFormat("dd-MM-yyyy", Locale.US)
//            val myDate = df.parse(et_edit_tanggal.text.toString())
            listMemo.apply {
                isimemo = et_edit_memo.text.toString()
//                tanggal = myDate.toString()
                tanggal = et_edit_tanggal.text.toString()
            }

            GlobalScope.launch {
                val memoUpdate = db.memoDao().updateMemo(listMemo)
            }
            dismiss()
            (activity as ProfileUser).fetchData()
        }

        tv_hapus.setOnClickListener {
            GlobalScope.launch {
                val delete = db.memoDao().deleteMemo(listMemo)
            }
            dismiss()
            (activity as ProfileUser).fetchData()
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentEditMemo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        lateinit var tanggal: String
        lateinit var isiMemo: String
        lateinit var listMemo: Memo
        fun newInstance(date: String, memo: String, list: Memo): FragmentEditMemo {
            tanggal = date
            isiMemo = memo
            listMemo = list
            return FragmentEditMemo()
        }
//        fun newInstance(param1: String, param2: String) =
//            FragmentEditMemo().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}
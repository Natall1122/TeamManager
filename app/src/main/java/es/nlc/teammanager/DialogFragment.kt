package es.nlc.teammanager

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class DialogFragment: DialogFragment(){

    private lateinit var mListener: DialogListener

    interface DialogListener{
        fun onDialogClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is DialogListener){
            mListener = context
        }else{
            throw Exception("must implement interface")
        }
    }

    override fun onCreateDialog(savedInstantState: Bundle?): Dialog {
        return activity?.let{
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder
                .setView(inflater.inflate(R.layout.fragment_dialog,null))
                .setPositiveButton("OK"){ dialog, id ->
                    mListener.onDialogClick()
                }
            builder.create()
        }?: throw IllegalStateException("Activitat No pot ser nula")
    }

}
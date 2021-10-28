package com.example.week6_sec4_headsuproom

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.week6_sec4_headsuproom.editDB.Update_Delete
import com.example.week6_sec4_headsuproom.models.Celebrity
import com.example.week6_sec4_headsuproom.models.CelebrityDataBase
import kotlinx.android.synthetic.main.item_row.view.*

class RV_Adapter (val activity: Update_Delete, private val celebrities:List<Celebrity>): RecyclerView.Adapter<RV_Adapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val celeId = celebrities[position].id
        val celeName = celebrities[position].name
        val celeTs = "${celebrities[position].taboo1} \n ${celebrities[position].taboo2} \n ${celebrities[position].taboo3}  "


        holder.itemView.apply {
            text_ViewN.text = celeName
            text_ViewTs.text=celeTs
            var myDBRoom= CelebrityDataBase.getInstance(activity)

            //-----------------------------AlertDialog---------------------------------------------------
            editIcon.setOnClickListener {
                var alt = AlertDialog.Builder(activity)
                alt.setTitle("Update Celebrity ")
                val mLayout  = LinearLayout(activity)
                val mEtName = EditText(activity)
                val mEtT1 = EditText(activity)
                val mEtT2 = EditText(activity)
                val mEtT3 = EditText(activity)

                mEtName.setSingleLine()
                mEtT1.setSingleLine()
                mEtT2.setSingleLine()
                mEtT3.setSingleLine()

                mLayout.orientation = LinearLayout.VERTICAL
                mLayout.addView(mEtName)
                mLayout.addView(mEtT1)
                mLayout.addView(mEtT2)
                mLayout.addView(mEtT3)
                mLayout.setPadding(50, 40, 50, 10)



                // --------------------Positive button text and action

                alt.setPositiveButton("Save", DialogInterface.OnClickListener { _, _ ->

                    var inputName = mEtName.text.toString()
                    if(inputName=="")inputName=celeName

                    var inputT1 = mEtT1.text.toString()
                    if(inputT1=="")inputT1=celebrities[position].taboo1

                    var inputT2 = mEtT2.text.toString()
                    if(inputT2=="")inputT2=celebrities[position].taboo2

                    var inputT3 = mEtT3.text.toString()
                    if(inputT3=="")inputT3=celebrities[position].taboo3

                    myDBRoom.CelebrityDao().updateCelebrity(Celebrity(celeId,inputName,inputT1,inputT2,inputT3))

                    activity.updateList()//text_View.text =input

                })


                // ------------------negative button text and action
                alt.setNegativeButton("Cansel", DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()

                })

                val alt1: AlertDialog = alt.create()
                alt1.setCanceledOnTouchOutside(false)
                alt1.setView(mLayout)
                alt1.show()
            }
            //------------------------------------------------------------------------------------
            deleteIcon.setOnClickListener {
                myDBRoom.CelebrityDao().deleteCelebrity(Celebrity(celeId))
                activity.updateList()
            }

        }
    }

    override fun getItemCount() = celebrities.size
}

package com.example.tasktodo.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktodo.R

class RecyclerViewCasesAdapter(private var context: Context, private var cases: ArrayList<Case>) : RecyclerView.Adapter<RecyclerViewCasesAdapter.CaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseViewHolder {
       return CaseViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cases, parent, false))
    }

    override fun onBindViewHolder(holder: CaseViewHolder, position: Int) {
        val currentCase = cases[position]
        holder.title.text = currentCase.title
        holder.description.text = currentCase.description
        holder.buttonDonate.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://ui.pse.com.co/ui/")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return cases.size
    }

    inner class CaseViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val title: TextView = view.findViewById(R.id.titleCase)
        val description: TextView = view.findViewById(R.id.descriptionCase)
        val buttonDonate: Button = view.findViewById(R.id.buttonDonate)

    }

}
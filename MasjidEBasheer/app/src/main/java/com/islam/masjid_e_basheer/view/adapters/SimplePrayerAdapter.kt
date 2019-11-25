package com.islam.masjid_e_basheer.view.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.islam.masjid_e_basheer.R
import com.islam.masjid_e_basheer.model.entity.SimplePrayer
import com.islam.masjid_e_basheer.view.inflate
import kotlinx.android.synthetic.main.prayer_table_row.view.*

class SimplePrayerAdapter:  RecyclerView.Adapter<SimplePrayerAdapter.SimplePrayerHolder>() {

    private var simplePrayerList = mutableListOf<SimplePrayer>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimplePrayerHolder {
        val inflatedView = parent.inflate(R.layout.prayer_table_row, false)  // // executes the extension function
        return SimplePrayerHolder(inflatedView)
    }

    override fun getItemCount() = simplePrayerList.size

    override fun onBindViewHolder(holder: SimplePrayerHolder, position: Int) {
        val simplePrayerItem = simplePrayerList[position]
        holder.bind(simplePrayerItem)
    }

    fun setData(newData: List<SimplePrayer>){
        simplePrayerList.clear()
        simplePrayerList.addAll(newData)
        notifyDataSetChanged()
    }

    class SimplePrayerHolder(val view: View): RecyclerView.ViewHolder(view){

        private var id: String? = null
        private var from: String? = null
        private var to: String? = null
        private var fajr: String? = null
        private var dhuhr: String? = null
        private var asr: String? = null
        private var maghrib: String? = null
        private var isha: String? = null

        fun bind(simplePrayer: SimplePrayer){
            this.id = simplePrayer.id
            this.from = simplePrayer.from
            this.to = simplePrayer.to
            this.fajr = simplePrayer.fajr
            this.asr = simplePrayer.asr
            this.maghrib = simplePrayer.maghrib
            this.isha = simplePrayer.isha

            view.fromTextView.text = from
            view.toTextView.text = to
            view.fajrTextView.text = fajr
            view.dhuhrTextView.text = dhuhr
            view.asrTextView.text = asr
            view.maghribTextView.text = maghrib
            view.ishaTextView.text = isha
        }
    }

}
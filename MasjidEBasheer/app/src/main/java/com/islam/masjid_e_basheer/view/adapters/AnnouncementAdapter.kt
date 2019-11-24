package com.islam.masjid_e_basheer.view.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.islam.masjid_e_basheer.R
import com.islam.masjid_e_basheer.model.entity.Announcement
import com.islam.masjid_e_basheer.view.inflate
import kotlinx.android.synthetic.main.announcement_item.view.*

class AnnouncementAdapter(): RecyclerView.Adapter<AnnouncementAdapter.AnnouncementHolder>() {

    private var announcementList = mutableListOf<Announcement>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnnouncementHolder {
        val inflatedView = parent.inflate(R.layout.announcement_item, false)  // // executes the extension function
        return AnnouncementHolder(inflatedView)
    }

    override fun getItemCount() = announcementList.size

    override fun onBindViewHolder(holder: AnnouncementHolder, position: Int) {
        val announcementItem = announcementList[position]
        holder.bind(announcementItem)
    }

    fun setData(newData: List<Announcement>){
        announcementList.clear()
        announcementList.addAll(newData)
        notifyDataSetChanged()
    }

    class AnnouncementHolder(val view: View): RecyclerView.ViewHolder(view){

        private var id: String? = null
        private var title: String? = null
        private var description: String? = null

        fun bind(announcement: Announcement){
            this.id = announcement.id
            this.title = announcement.title
            this.description = announcement.description

            view.titleTextView.text = title
            view.descriptionTextView.text = description
        }
    }
}
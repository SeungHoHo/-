package fastcampus.aop.aop_part5_chapter06_repeat.presentation.trackinghistory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.TrackingDetail
import fastcampus.aop.aop_part5_chapter06_repeat.databinding.ItemTrackingHistoryBinding

class TrackingHistoryAdapter : RecyclerView.Adapter<TrackingHistoryAdapter.ViewHolder>() {

    var data: List<TrackingDetail> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemTrackingHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val binding: ItemTrackingHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(info: TrackingDetail) {
            binding.timeStampTextView.text = info.timeString
            binding.stateTextView.text = info.kind
            binding.locationTextView.text = "@${info.where}"
        }
    }
}
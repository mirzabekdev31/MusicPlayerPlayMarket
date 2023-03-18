package uz.gita.mirzabek.example.musicplayerplaymarket.presentation.ui.adapter

import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.mirzabek.example.musicplayerplaymarket.data.mapper.getMusicDataByPosition
import uz.gita.mirzabek.example.musicplayerplaymarket.data.model.MusicData
import uz.gita.mirzabek.example.musicplayerplaymarket.databinding.ItemMusicBinding


class MusicAdapter () : RecyclerView.Adapter<MusicAdapter.MyCursorViewHolder>() {
    var cursor : Cursor ?= null
    private var selectMusicPositionListener : ((Int) -> Unit)?= null

    inner class MyCursorViewHolder(private val binding : ItemMusicBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                selectMusicPositionListener?.invoke(absoluteAdapterPosition)
            }
        }

        fun bind() {
            cursor?.getMusicDataByPosition(absoluteAdapterPosition)?.let {
                binding.textMusicName.text = it.title
                binding.textArtistName.text = it.artist
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        MyCursorViewHolder(ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MyCursorViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = cursor?.count ?: 0

    fun setSelectMusicPositionListener(block : (Int) -> Unit) {
        selectMusicPositionListener = block
    }
}
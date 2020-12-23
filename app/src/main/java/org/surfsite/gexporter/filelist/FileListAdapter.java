package org.surfsite.gexporter.filelist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.tracks.exporter.R;

import java.io.File;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.FileViewHolder> {

    private File[] files = new File[0];

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FileViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_file_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder fileViewHolder, int i) {
        fileViewHolder.setData(files[i], i == files.length - 1);
    }

    @Override
    public int getItemCount() {
        return files.length;
    }

    public void setFiles(File[] files) {
        this.files = files;
        notifyDataSetChanged();
    }

    protected class FileViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvSize;
        private View fileItemDivider;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.file_name);
            tvSize = itemView.findViewById(R.id.file_size);
            fileItemDivider = itemView.findViewById(R.id.fileItemDivider);
        }

        public void setData(File file, boolean isLasIndex) {
            tvName.setText(file.getName());
            if (file.exists()) {

                float fileSize = Math.round((float) file.length() / 1024f * 100f) / 100f; // in kb
                if (fileSize > 1024) {
                    // 1 MB or more -> display mb instead of kb
                    fileSize = Math.round(fileSize / 1024 * 100f) / 100f;
                    tvSize.setText(tvSize.getContext().getString(R.string.file_size_mb, "" + fileSize));
                } else {
                    tvSize.setText(tvSize.getContext().getString(R.string.file_size_kb, "" + fileSize));
                }
            } else {
                tvSize.setText("");
            }

            if (isLasIndex) {
                fileItemDivider.setVisibility(View.GONE);
            } else {
                fileItemDivider.setVisibility(View.VISIBLE);
            }
        }
    }
}

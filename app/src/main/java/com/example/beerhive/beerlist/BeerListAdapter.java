package com.example.beerhive.beerlist;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import androidx.annotation.NonNull;
        import androidx.core.app.NavUtils;
        import androidx.databinding.DataBindingUtil;
        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.load.engine.DiskCacheStrategy;
        import com.example.beerhive.R;
        import com.example.beerhive.databinding.BeerItemLayoutBinding;
        import com.example.beerhive.network.model.BeerResponse;
        import com.example.beerhive.network.model.Volume;

        import java.util.List;

public class BeerListAdapter extends RecyclerView.Adapter<BeerListAdapter.BeerListViewHolder> {

    private BeerItemClickListener beerItemClickListener;
    private List<BeerResponse> beerResponseList;

    public BeerListAdapter(BeerItemClickListener beerItemClickListener, List<BeerResponse> beerResponseList) {
        this.beerItemClickListener = beerItemClickListener;
        this.beerResponseList = beerResponseList;
    }

    @NonNull
    @Override
    public BeerListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BeerItemLayoutBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.beer_item_layout, parent, false);
        return new BeerListViewHolder(binding, beerItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BeerListViewHolder holder, int position) {
        BeerResponse beerResponse = beerResponseList.get(position);
        holder.bindData(beerResponse);
        holder.setBeerResponse(beerResponse);
    }

    @Override
    public int getItemCount() {
        return beerResponseList != null ? beerResponseList.size() : 0;
    }

    public interface BeerItemClickListener {
        void onBeerItemClicked(BeerResponse catEntry);
    }

    static class BeerListViewHolder extends RecyclerView.ViewHolder {
        private BeerItemClickListener beerItemClickListener;
        private BeerResponse beerResponse;

        public void setBeerResponse(BeerResponse beerResponse) {
            this.beerResponse = beerResponse;
        }

        BeerItemLayoutBinding binding;

        public BeerListViewHolder(@NonNull BeerItemLayoutBinding binding, BeerItemClickListener beerItemClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.beerItemClickListener = beerItemClickListener;
            binding.beerItemContainer.setOnClickListener(v -> beerItemClickListener.onBeerItemClicked(beerResponse));
        }

        public void bindData(BeerResponse beerResponse) {
            binding.beerNameTextView.setText(beerResponse.getName());

            binding.valueOfBeerTextView.setText(beerResponse.getVolume().getValue().toString().concat(" ").concat(beerResponse.getVolume().getUnit()));

            Glide.with(binding.beerImageView.getContext()).load(beerResponse.getImageUrl()).dontAnimate().fitCenter().diskCacheStrategy(
                    DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.place_holder).error(R.drawable.place_holder).into(binding.beerImageView);
        }
    }
}

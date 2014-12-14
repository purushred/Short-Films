package com.smart.shortfilms.util;

import android.util.Log;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

import com.smart.shortfilms.adapter.ImageListAdapter;
import com.smart.shortfilms.command.ImageListCommand;

public class ListEndlessScrollListener implements OnScrollListener {

    private final int sortBy;
    private int visibleThreshold = 4;

	// The total number of items in the dataset after the last load
	private int previousTotalItemCount = 0;
	private boolean loading = true;
	private ImageListAdapter adapter = null;

    public ListEndlessScrollListener(ImageListAdapter adapter,int sortBy) {
		this.adapter = adapter;
        this.sortBy = sortBy;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

		if (loading) {
			if (totalItemCount > previousTotalItemCount) {
				loading = false;
				previousTotalItemCount = totalItemCount;
			}
		}
		else
		{
			if((firstVisibleItem>0)&&(totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
				new ImageListCommand(adapter,sortBy).execute();
				loading = true;
			}
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}
}
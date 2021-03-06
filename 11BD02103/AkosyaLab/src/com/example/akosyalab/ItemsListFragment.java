package com.example.akosyalab;

import java.util.ArrayList;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class ItemsListFragment extends Fragment {
	  private ArrayAdapter<Item> adapterItems;
	  private ListView lvItems;
	  private OnListItemSelectedListener listener;

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    ArrayList<Item> items = Item.getItems();
	    adapterItems = new ArrayAdapter<Item>(getActivity(),
	        android.R.layout.simple_list_item_activated_1, items);
	  }
	  	  public interface OnListItemSelectedListener {
	    public void onItemSelected(Item item);
	  }
	  	@Override
	    public void onAttach(Activity activity) {
	      super.onAttach(activity);
	      if (activity instanceof OnListItemSelectedListener) {
	        listener = (OnListItemSelectedListener) activity;
	      } else {
	        throw new ClassCastException(
	            activity.toString()
	                + " must implement ItemsListFragment.OnListItemSelectedListener");
	      }
	    }
	  	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	      // Inflate view
	      View view = inflater.inflate(R.layout.fragment_items_list, container,
	          false);
	      lvItems = (ListView) view.findViewById(R.id.lvItems);
	      lvItems.setAdapter(adapterItems);
	      lvItems.setOnItemClickListener(new OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> adapterView, View item, 
	            int position, long rowId) {
	          
	          Item i = adapterItems.getItem(position);
	           
	          
              listener.onItemSelected(i);
	          }
			});
	      return view;
	    }
	  	public void setActivateOnItemClick(boolean activateOnItemClick) {

	  	    lvItems.setChoiceMode(
	  	        activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
	  	            : ListView.CHOICE_MODE_NONE);
	  	  }
	}
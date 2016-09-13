/*
 * Copyright (c) 2015 Hannes Dorfmann.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.nannan.doit.lib.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.nannan.doit.base.BaseRecyclerViewHolder;

/**
 * This delegate provide method to hook in this delegate to {@link RecyclerView.Adapter} lifecycle.
 * This "hook in" mechanism is provided by {@link AdapterDelegatesManager} and that is the
 * component
 * you have to use.
 *
 * @param <T> The type of the mData source
 * @author Hannes Dorfmann
 * @since 1.0
 */
public interface AdapterDelegate<T> {
  /**
   * Get the view type integer. Must be unique within every Adapter
   *
   * @return the integer representing the view type
   */
  public int getItemViewType();

  /**
   * Called to determine whether this AdapterDelegate is the responsible for the given mData
   * element.
   *
   * @param item The mData source of the Adapter
   * @param position The position in the datasource
   * @return true, if this item is responsible,  otherwise false
   */
  public boolean isForViewType(@NonNull T item, int position);

  /**
   * Creates the  {@link RecyclerView.ViewHolder} for the given mData source item
   *
   * @param parent The ViewGroup parent of the given datasource
   * @return The new instantiated {@link RecyclerView.ViewHolder}
   */
  @NonNull
  public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent);

  /**
   * Called to bind the {@link RecyclerView.ViewHolder} to the item of the datas source set
   *
   * @param item The mData source
   * @param position The position in the datasource
   * @param holder The {@link RecyclerView.ViewHolder} to bind
   */
  public void onBindViewHolder(@NonNull T item, int position,
      @NonNull BaseRecyclerViewHolder holder);
}

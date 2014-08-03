package com.whiteout.myworship.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.whiteout.myworship.fragments.ChurchPickerFragment;
import com.whiteout.myworship.fragments.EmptyFragment;

/**
 * Kendrick Cline 8/2/14.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 3;

    /** Is a more memory efficient way to handle multiple Fragments
        Unlike normal arrays of Objects, there can be gaps in the indices
            WHY? -- because it avoids auto-boxing keys
                    its data structure doesn't rely on an extra entry object for each mapping
        developer.android.com/reference/android/util/SparseArray.html
    */
    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Register fragment when item is instantiated
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    // Unregister when the item is inactive
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }


    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return ChurchPickerFragment.newInstance(0, "Page 1");
            default:
                return EmptyFragment.newInstance(position + 1);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Home";
            default:
                return "Test";

        }
    }

    public Fragment getRegisteredFragment(int position) { return registeredFragments.get(position);}

}

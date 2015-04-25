package com.ps.citizen3.ui;

import android.animation.ValueAnimator;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ps.citizen3.R;
import com.ps.citizen3.data.api.GoogleCivicModel.Channel;
import com.ps.citizen3.data.api.GoogleCivicModel.Office;
import com.ps.citizen3.data.api.GoogleCivicModel.Official;
import com.ps.citizen3.data.api.GoogleCivicModel.RepResult;
import com.ps.citizen3.util.OfficialHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;

public class RepAdapter extends RecyclerView.Adapter<RepAdapter.ViewHolder> {
    private RepResult result;

    public RepAdapter(RepResult result) {
        this.result = result;

        // Data prep
        OfficialHelper.setOfficesForOfficials(result);
        result.setOfficials(OfficialHelper.orderOfficials(result));
    }

    @Override
    public int getItemCount() {
        return result.getOfficials().size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        Official official = result.getOfficials().get(i);
        Office office = official.getOffice();

        if (official.getPhotoUrl() != null) {
            Picasso.with(viewHolder.context).load(official.getPhotoUrl()).into(viewHolder.imageView);
        }

        viewHolder.nameTextView.setText(official.getName());
        List<String> officeInfo = OfficialHelper.getOfficeInfo(office.getName());
        viewHolder.officeTextView.setText(officeInfo.get(0));

        if (official.getEmails().size() > 0) {
            final String email = official.getEmails().get(0);
            viewHolder.emailButton.setVisibility(View.VISIBLE);
            viewHolder.emailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String uri = "mailto:" + email;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(uri));
                    viewHolder.context.startActivity(intent);
                }
            });
        }
        if (official.getPhones().size() > 0) {
            final String phone = official.getPhones().get(0);
            viewHolder.phoneButton.setVisibility(View.VISIBLE);
            viewHolder.phoneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String uri = "tel:" + phone;
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(uri));
                    viewHolder.context.startActivity(intent);
                }
            });
        }
        for (final Channel channel : official.getChannels()) {
            String type = channel.getType();
            if (type.contains("GooglePlus")) {
                viewHolder.googleplusButton.setVisibility(View.VISIBLE);
                viewHolder.googleplusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setClassName("com.google.android.apps.plus",
                                    "com.google.android.apps.plus.phone.UrlGatewayActivity");
                            intent.putExtra("customAppUri", channel.getId());
                            viewHolder.context.startActivity(intent);
                        } catch(ActivityNotFoundException e) {
                            viewHolder.context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/" + channel.getId() +"/posts")));
                        }
                    }
                });
            } else if (type.contains("Facebook")) {
                viewHolder.facebookButton.setVisibility(View.VISIBLE);
                viewHolder.facebookButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            viewHolder.context.getPackageManager().getPackageInfo("com.facebook.katana",0);
                            viewHolder.context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + channel.getId())));
                        } catch (Exception e) {
                            viewHolder.context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + channel.getId())));
                        }
                    }
                });
            } else if (type.contains("Twitter")) {
                viewHolder.twitterButton.setVisibility(View.VISIBLE);
                viewHolder.twitterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            viewHolder.context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + channel.getId())));
                        }catch (Exception e) {
                            viewHolder.context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + channel.getId())));
                        }
                    }
                });
            } else if (type.contains("YouTube")) {
                viewHolder.youtubeButton.setVisibility(View.VISIBLE);
                viewHolder.youtubeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewHolder.context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/user/" + channel.getId())));
                    }
                });
            }
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rep_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.rep_name) protected TextView nameTextView;
        @InjectView(R.id.rep_office) protected TextView officeTextView;
        @InjectView(R.id.rep_image) protected ImageView imageView;
        @InjectView(R.id.rep_email) protected ImageView emailButton;
        @InjectView(R.id.rep_phone) protected ImageView phoneButton;
        @InjectView(R.id.rep_twitter) protected ImageView twitterButton;
        @InjectView(R.id.rep_facebook) protected ImageView facebookButton;
        @InjectView(R.id.rep_google_plus) protected ImageView googleplusButton;
        @InjectView(R.id.rep_youtube) protected ImageView youtubeButton;
        @InjectView(R.id.rep_expandedView) protected LinearLayout expandedView;

        private int originalHeight = 0;
        private boolean isViewExpanded = false;
        private int expandedViewHeight = 0;

        protected Context context;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            context = v.getContext();
            ButterKnife.inject(this, v);

            if (!isViewExpanded) {
                // Set Views to View.GONE and .setEnabled(false)
                phoneButton.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                expandedViewHeight = phoneButton.getMeasuredHeight();
                expandedView.setVisibility(View.GONE);
                expandedView.setEnabled(false);
            }

            emailButton.setVisibility(View.GONE);
            phoneButton.setVisibility(View.GONE);
            twitterButton.setVisibility(View.GONE);
            facebookButton.setVisibility(View.GONE);
            googleplusButton.setVisibility(View.GONE);
            youtubeButton.setVisibility(View.GONE);
        }

        @Override
        public void onClick(final View view) {
            // If the originalHeight is 0 then find the height of the View being used
            // This would be the height of the cardview
            if (originalHeight == 0) {
                originalHeight = view.getHeight();
            }

            // Declare a ValueAnimator object
            ValueAnimator valueAnimator;
            if (!isViewExpanded) {
                expandedView.setVisibility(View.VISIBLE);
                expandedView.setEnabled(true);
                isViewExpanded = true;
                // These values in this method can be changed to expand however much you like
                valueAnimator = ValueAnimator.ofInt(originalHeight, originalHeight + expandedViewHeight);
            } else {
                isViewExpanded = false;
                valueAnimator = ValueAnimator.ofInt(originalHeight + expandedViewHeight, originalHeight);

                Animation a = new AlphaAnimation(1.00f, 0.00f); // Fade out

                a.setDuration(200);
                // Set a listener to the animation and configure onAnimationEnd
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        expandedView.setVisibility(View.INVISIBLE);
                        expandedView.setEnabled(false);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                // Set the animation on the custom view
                expandedView.startAnimation(a);
            }

            RecyclerView recyclerView = (RecyclerView)view.getParent();
            final int index = recyclerView.getChildPosition(view);
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();

            valueAnimator.setDuration(200);
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    Integer value = (Integer) animation.getAnimatedValue();
                    view.getLayoutParams().height = value.intValue();
                    view.requestLayout();
                    linearLayoutManager.scrollToPosition(index);
                }
            });


            valueAnimator.start();

        }
    }
}
package com.knotenpunkt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GodiplanListAdapter extends RecyclerView.Adapter<GodiplanListAdapter.GetHolder> {

    List<GodiplanItem> godiplanItemList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public GodiplanListAdapter(List<GodiplanItem> godiplanItemList) {
        this.godiplanItemList = godiplanItemList;
    }

    @NonNull
    @Override
    public GetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.godiplan_listview, parent, false);
        return new GetHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull GetHolder holder, int position) {
        GodiplanItem getGodiplanItem = godiplanItemList.get(position);
        holder.setmDatum(getGodiplanItem.getDatum());
        holder.setmFr_Sabbat(getGodiplanItem.getFr_Sabbat());
        holder.setmModeration(getGodiplanItem.getModeration());
        holder.setmKidsGo(getGodiplanItem.getKidsGo());
        //holder.setmMusik(getGodiplanItem.getMusik());
        holder.setmGespreachsltng(getGodiplanItem.getGespreachsltng());
        holder.setmPredigt(getGodiplanItem.getPredigt());
        holder.setmKindermoment(getGodiplanItem.getKindermoment());
        holder.setmZeit(getGodiplanItem.getZeit());
        holder.setmOrt(getGodiplanItem.getOrt());
        holder.setmPutzdienst(getGodiplanItem.getPutzdienst());
    }

    @Override
    public int getItemCount() {
        return godiplanItemList.size();
    }

    public class GetHolder extends RecyclerView.ViewHolder {

        TextView mDatum;
        TextView mFr_Sabbat;
        TextView mModeration;
        TextView mKidsGo;
        //TextView mMusik;
        TextView mGespreachsltng;
        TextView mPredigt;
        TextView mKindermoment;
        TextView mZeit;
        TextView mOrt;
        TextView mPutzdienst;
        View view;

        public GetHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void setmDatum(String datum) {
            mDatum = view.findViewById(R.id.DatumExcelTableLbl);
            mDatum.setText(datum);
        }

        public void setmFr_Sabbat(String fr_sabbat) {
            mFr_Sabbat = view.findViewById(R.id.Fr_SabbatExcelTableLbl);
            if (!fr_sabbat.equals("")) {
                mFr_Sabbat.setText(fr_sabbat);
            }
            if (fr_sabbat.equals("")) {
                mFr_Sabbat.setText("-");
            }
        }

        public void setmModeration(String moderation) {
            mModeration = view.findViewById(R.id.ModerationExcelTableLbl);
            if (!moderation.equals("")) {
                mModeration.setText(moderation);
            }
            if (moderation.equals("")) {
                mModeration.setText("-");
            }
        }

        public void setmKidsGo(String kidsGo) {
            mKidsGo = view.findViewById(R.id.KidsGoExcelTableLbl);
            if (!kidsGo.equals("")) {
                mKidsGo.setText(kidsGo);
            }
            if (kidsGo.equals("")) {
                mKidsGo.setText("-");
            }
        }

//        public void setmMusik(String musik) {
//            mMusik = view.findViewById(R.id.MusikExcelTableLbl);
//            if (!musik.equals("")) {
//                mMusik.setText(musik);
//            }
//            if (musik.equals("")) {
//                mMusik.setText("-");
//            }
//        }

        public void setmGespreachsltng(String gespreachsltng) {
            mGespreachsltng = view.findViewById(R.id.GespreachsltngExcelTableLbl);
            if (!gespreachsltng.equals("")) {
                mGespreachsltng.setText(gespreachsltng);
            }
            if (gespreachsltng.equals("")) {
                mGespreachsltng.setText("-");
            }
        }

        public void setmPredigt(String predigt) {
            mPredigt = view.findViewById(R.id.PredigtExcelTableLbl);
            if (!predigt.equals("")) {
                if (predigt.length() < 20) {
                    mPredigt.setText(predigt);
                    mPredigt.setTextSize(17);
                }
                else {
                    mPredigt.setText(predigt);
                    mPredigt.setTextSize(13);
                    mPredigt.setY(9);
                }
            }
            if (predigt.equals("")) {
                mPredigt.setText("-");
            }
        }

        public void setmKindermoment(String kindermoment) {
            mKindermoment = view.findViewById(R.id.KindermomentExcelTableLbl);
            if (!kindermoment.equals("")) {
                mKindermoment.setText(kindermoment);
            }
            if (kindermoment.equals("")) {
                mKindermoment.setText("-");
            }
        }

        public void setmZeit(String zeit) {
            mZeit = view.findViewById(R.id.ZeitExcelTableLbl);
            if (!zeit.equals("")) {
                mZeit.setText(zeit);
            }
            if (zeit.equals("")) {
                mZeit.setText("-");
            }
        }

        public void setmOrt(String ort) {
            mOrt = view.findViewById(R.id.OrtExcelTableLbl);
            if (!ort.equals("")) {
                mOrt.setText(ort);
            }
            if (ort.equals("")) {
                mOrt.setText("-");
            }
        }

        public void setmPutzdienst(String putzdienst) {
            mPutzdienst = view.findViewById(R.id.PutzdienstExcelTableLbl);
            if (!putzdienst.equals("")) {
                mPutzdienst.setText(putzdienst);
            }
            if (putzdienst.equals("")) {
                mPutzdienst.setText("-");
            }
        }
    }
}

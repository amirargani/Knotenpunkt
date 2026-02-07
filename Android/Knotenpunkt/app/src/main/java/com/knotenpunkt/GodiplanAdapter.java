package com.knotenpunkt;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class GodiplanAdapter extends RecyclerView.Adapter<GodiplanAdapter.GetHolder> {

    List<GodiplanItem> godiplanItemList;

    public GodiplanAdapter(List<GodiplanItem> godiplanItemList) {
        this.godiplanItemList = godiplanItemList;
    }

    @NonNull
    @Override
    public GetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.godiplan_item, parent, false);
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
        TextView mMusik;
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
        }

        public void setmDatum(String datum) {
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            String date = df.format(Calendar.getInstance().getTime());
            String y = datum.substring(6, 10);
            String m = datum.substring(3, 5);
            String d = datum.substring(0, 2);
            String datumItem = y + m + d;
            int NumDate = Integer.parseInt(date);
            int NumDatum = Integer.parseInt(datumItem);
            mDatum = view.findViewById(R.id.DatumExcelLbl);
            mDatum.setBackgroundColor(Color.parseColor("#8D1A24"));
            mDatum.setText(datum);
            if (NumDatum  > NumDate) {
                mDatum = view.findViewById(R.id.DatumExcelLbl);
                mDatum.setBackgroundColor(Color.parseColor("#616263"));
                mDatum.setText(datum);
            }
            if (NumDatum < NumDate) {
                mDatum = view.findViewById(R.id.DatumExcelLbl);
                mDatum.setBackgroundColor(Color.parseColor("#616263"));
                mDatum.setText(datum);
            }
        }

        public void setmFr_Sabbat(String fr_sabbat) {
            mFr_Sabbat = view.findViewById(R.id.Fr_SabbatExcelLbl);
            if (!fr_sabbat.equals("")) {
                mFr_Sabbat.setText(fr_sabbat);
            }
            if (fr_sabbat.equals("")) {
                mFr_Sabbat.setText("-");
            }
        }

        public void setmModeration(String moderation) {
            mModeration = view.findViewById(R.id.ModerationExcelLbl);
            if (!moderation.equals("")) {
                mModeration.setText(moderation);
            }
            if (moderation.equals("")) {
                mModeration.setText("-");
            }
        }

        public void setmKidsGo(String kidsGo) {
            mKidsGo = view.findViewById(R.id.KidsGoExcelLbl);
            if (!kidsGo.equals("")) {
                mKidsGo.setText(kidsGo);
            }
            if (kidsGo.equals("")) {
                mKidsGo.setText("-");
            }
        }

        public void setmMusik(String musik) {
            mMusik = view.findViewById(R.id.MusikExcelLbl);
            if (!musik.equals("")) {
            mMusik.setText(musik);
            }
            if (musik.equals("")) {
                mMusik.setText("-");
            }
        }

        public void setmGespreachsltng(String gespreachsltng) {
            mGespreachsltng = view.findViewById(R.id.GespreachsltngExcelLbl);
            if (!gespreachsltng.equals("")) {
                mGespreachsltng.setText(gespreachsltng);
            }
            if (gespreachsltng.equals("")) {
                mGespreachsltng.setText("-");
            }
        }

        public void setmPredigt(String predigt) {
            mPredigt = view.findViewById(R.id.PredigtExcelLbl);
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
            mKindermoment = view.findViewById(R.id.KindermomentExcelLbl);
            if (!kindermoment.equals("")) {
                mKindermoment.setText(kindermoment);
            }
            if (kindermoment.equals("")) {
                mKindermoment.setText("-");
            }
        }

        public void setmZeit(String zeit) {
            mZeit = view.findViewById(R.id.ZeitExcelLbl);
            if (!zeit.equals("")) {
                mZeit.setText(zeit);
            }
            if (zeit.equals("")) {
                mZeit.setText("-");
            }
        }

        public void setmOrt(String ort) {
            mOrt = view.findViewById(R.id.OrtExcelLbl);
            if (!ort.equals("")) {
                mOrt.setText(ort);
            }
            if (ort.equals("")) {
                mOrt.setText("-");
            }
        }

        public void setmPutzdienst(String putzdienst) {
            mPutzdienst = view.findViewById(R.id.PutzdienstExcelLbl);
            if (!putzdienst.equals("")) {
                mPutzdienst.setText(putzdienst);
            }
            if (putzdienst.equals("")) {
                mPutzdienst.setText("-");
            }
        }
    }
}

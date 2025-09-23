package com.knotenpunkt;

import android.content.ClipData;
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

public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.GetHolder> {

    List<CalendarItem> calendarItemList;

    public CalenderAdapter(List<CalendarItem> calendarItemList) {
        this.calendarItemList = calendarItemList;
    }

    @NonNull
    @Override
    public GetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item, parent, false);
        return new GetHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull GetHolder holder, int position) {
        CalendarItem getCalendarItem = calendarItemList.get(position);
        holder.setmDatum(getCalendarItem.getDatum());
        holder.setmZeit(getCalendarItem.getZeit());
        holder.setmTitel(getCalendarItem.getTitel());
        holder.setmBeschreibung(getCalendarItem.getBeschreibung());
        holder.setmOrt(getCalendarItem.getOrt());
    }

    @Override
    public int getItemCount() {
        return calendarItemList.size();
    }

    public class GetHolder extends RecyclerView.ViewHolder {

        TextView mDatum;
        TextView mZeit;
        TextView mTitel;
        TextView mBeschreibung;
        TextView mOrt;
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
            mDatum = view.findViewById(R.id.StartdatumCalendarlLbl);
            mDatum.setBackgroundColor(Color.parseColor("#0045C4"));
            mDatum.setText(datum);
            if (NumDatum  > NumDate) {
                mDatum = view.findViewById(R.id.StartdatumCalendarlLbl);
                mDatum.setBackgroundColor(Color.parseColor("#616263"));
                mDatum.setText(datum);
            }
            if (NumDatum < NumDate) {
                mDatum = view.findViewById(R.id.StartdatumCalendarlLbl);
                mDatum.setBackgroundColor(Color.parseColor("#616263"));
                mDatum.setText(datum);
            }
        }

        public void setmZeit(String zeit) {
            mZeit = view.findViewById(R.id.ZeitCalendarlLbl);
            mZeit.setText(zeit);
        }

        public void setmTitel(String titel) {
            mTitel = view.findViewById(R.id.TiteCalendarllLbl);
            mTitel.setText(titel);
        }

        public void setmBeschreibung(String beschreibung) {
            mBeschreibung = view.findViewById(R.id.BeschreibungCalendarllLbl);
            mBeschreibung.setText(beschreibung);
        }

        public void setmOrt(String ort) {
            mOrt = view.findViewById(R.id.OrtCalendarlLbl);
            mOrt.setText(ort);
        }
    }
}

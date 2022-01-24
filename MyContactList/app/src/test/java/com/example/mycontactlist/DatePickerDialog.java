package com.example.mycontactlist;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import java.util.Calendar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;

public class DatePickerDialog extends DialogFragment {
    Calender selectedDate;

    public interface SaveDteListener {
        void didFinishDatePickerDialog(Calender selectedTime);
    }

    public DatePickerDialog() {
        //Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.select_date, container);

        getDialog().setTitle("Select Date");
        selectedDate = Calender.getInstance();

        final CalenderView cv = view.findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                selectedDate.set(year,month,day);
            }
        });

        Button saveButton = view.findViewById(R.id.buttonSelect);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveItem(selectedDate);
            }
        });
        Button cancelButton = view.findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        private void saveItem(Calender selectedTime) {
            SaveDteListener activity = (SaveDteListener) getActivity();
            activity.didFinishDatePickerDialog(selectedTime);
            getDialog().dismiss();
        }
    }
}

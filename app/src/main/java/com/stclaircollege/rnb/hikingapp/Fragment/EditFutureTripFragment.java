package com.stclaircollege.rnb.hikingapp.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.greenfrvr.hashtagview.HashtagView;
import com.stclaircollege.rnb.hikingapp.Adapter.HikeAdapter;
import com.stclaircollege.rnb.hikingapp.Model.Hike;
import com.stclaircollege.rnb.hikingapp.Model.Participant;
import com.stclaircollege.rnb.hikingapp.Model.Trip;
import com.stclaircollege.rnb.hikingapp.R;
import com.stclaircollege.rnb.hikingapp.Util.DatabaseHandler;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditFutureTripFragment extends Fragment implements HikeAdapter.ItemHikeListener {
    private EditFutureTripListener mListener;
    private SupportPlaceAutocompleteFragment autocompleteFragment;
    private TextView text_start_date;
    private TextView text_end_date;
    private Spinner spinner_trip_organizer;
    private EditText edit_number_of_days;
    private EditText edit_accommodations;
    private EditText edit_reminders;
    private EditText edit_wildlife_seen;
    private EditText edit_highlights;
    private HashtagView hashtags;

    private String location = "";

    private RecyclerView recyclerView;
    private List<Hike>list_hikes = new ArrayList<>();
    private HikeAdapter adapter;

    private DatabaseHandler handler;

    List<Participant> list_members = new ArrayList<>();
    List<String> list_participants = new ArrayList<>();
    List<Integer> list_ids = new ArrayList<>();

    private Trip trip;

    public EditFutureTripFragment() {
    }

    @SuppressLint("ValidFragment")
    public EditFutureTripFragment(EditFutureTripListener listener, Trip trip) {
        mListener = listener;
        this.trip = trip;
        this.location = this.trip.location;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.list_members = new ArrayList<>();
        this.list_participants = new ArrayList<>();
        this.list_hikes = new ArrayList<>();
        handler = new DatabaseHandler(getContext());
        String[] ids_string = trip.hikes.split(",");
        for (int i = 0; i < ids_string.length; i++) {
            list_hikes.add(handler.getHike(Integer.parseInt(ids_string[i])));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_future_trip, container, false);
        bindView(view);
        setRecyclerView();
        return view;
    }

    void setRecyclerView() {
        adapter = new HikeAdapter(getContext(), list_hikes);
        adapter.setEventListener(EditFutureTripFragment.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    void bindView(View view) {
        autocompleteFragment = (SupportPlaceAutocompleteFragment)getChildFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                location = place.getAddress().toString();
            }
            @Override
            public void onError(Status status) {
            }
        });
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();
        autocompleteFragment.setFilter(typeFilter);
        autocompleteFragment.setText(trip.location);

        recyclerView = view.findViewById(R.id.recyclerView);
        text_start_date = view.findViewById(R.id.text_start_date);
        text_start_date.setText(trip.startDate);
        text_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SpinnerDatePickerDialogBuilder()
                        .context(getContext())
                        .callback(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                text_start_date.setText(String.format("%02d/%02d/%4d", dayOfMonth, monthOfYear + 1, year));
                            }
                        })
                        .defaultDate(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DATE))
                        .build()
                        .show();
            }
        });

        text_end_date = view.findViewById(R.id.text_end_date);
        text_end_date.setText(trip.endDate);
        text_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SpinnerDatePickerDialogBuilder()
                        .context(getContext())
                        .callback(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                text_end_date.setText(String.format("%02d/%02d/%4d", dayOfMonth, monthOfYear + 1, year));
                            }
                        })
                        .defaultDate(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DATE))
                        .build()
                        .show();
            }
        });

        spinner_trip_organizer = view.findViewById(R.id.spinner_trip_organizer);
        setSpinner();
        view.findViewById(R.id.btn_trip_organizer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .content("Input trip organizer")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input("", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if  (input.toString().trim().length()==0) return;
                                Participant participant = new Participant();
                                participant.name = input.toString();
                                handler.addParticipant(participant);
                                setSpinner();
                            }
                        }).show();
            }
        });

        hashtags = view.findViewById(R.id.hashtags);
        if (!trip.participants.isEmpty()) {
            String[] ids_string = trip.participants.split(",");
            for (int i = 0; i < ids_string.length; i++) {
                list_ids.add(Integer.parseInt(ids_string[i]));
                this.list_participants.add(list_members.get(Integer.parseInt(ids_string[i])).name);
                setHashTagData();
            }
        }

        view.findViewById(R.id.btn_participants).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectParticipants();
            }
        });

        edit_number_of_days = view.findViewById(R.id.edit_number_of_days);
        edit_number_of_days.setText(trip.noOfDays);

        edit_accommodations = view.findViewById(R.id.edit_accommodations);
        edit_accommodations.setText(trip.accommodations);

        view.findViewById(R.id.btn_add_hike).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_hikes.add(new Hike());
                setRecyclerView();
            }
        });

        view.findViewById(R.id.btn_remove_hike).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_hikes.size() > 1) {
                    list_hikes.remove(list_hikes.size() - 1);
                    setRecyclerView();
                }
            }
        });

        edit_reminders = view.findViewById(R.id.edit_reminders);
        edit_reminders.setText(trip.reminders);
        edit_wildlife_seen = view.findViewById(R.id.edit_wildlife_seen);
        edit_wildlife_seen.setText(trip.wildlifeSeen);
        edit_highlights = view.findViewById(R.id.edit_highlights);
        edit_highlights.setText(trip.highlights);
        view.findViewById(R.id.btn_update_trip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpdateTrip();
            }
        });
        view.findViewById(R.id.btn_complete_trip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCompleteTrip();
            }
        });
         view.findViewById(R.id.btn_share_trip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickShare();
            }
        });
    }

    private void onClickShare() {
      String organizer  = spinner_trip_organizer.getSelectedItem().toString();
        String participants = "";
        for (int i = 0; i < list_participants.size(); i++) {
            if (i > 0) participants = participants + ", ";
            participants = participants + list_participants.get(i);
        }

        String hikes = "";
        for (int i = 0; i < list_hikes.size(); i++) {
            String hike = "Hike Name: " + list_hikes.get(i).hikeName + "\n" +
                    "No of day hikes: " + list_hikes.get(i).noOfDayHikes + "\n" +
                    "No of bag nights: " + list_hikes.get(i).noOfBagNights + "\n" +
                    "Distance: " + list_hikes.get(i).distance + (list_hikes.get(i).unit == 0 ? "km" : "mile") + "\n" +
                    "Contact Info: " + list_hikes.get(i).contactInfo + "\n" +
                    "Daily breakdown: " + list_hikes.get(i).dailyBreakdown + "\n";
            hikes = hikes + hike;
        }

        String content = "Location: " + trip.location + "\n" +
                "Start date: " + trip.startDate + "\n" +
                "End date: " + trip.endDate + "\n" +
                "No. Of days: " + trip.noOfDays + "\n" +
                "Organizer: " + organizer + "\n" +
                "Participants: " + participants + "\n" +
                "Accommodations: " + trip.accommodations + "\n" +
                hikes +
                "Reminders: " + trip.reminders;

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Trip");
        emailIntent.putExtra(Intent.EXTRA_TEXT, content);
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    private void onSelectParticipants() {
        final List<String> names = new ArrayList<>();
        final List<Integer> ids = new ArrayList<>();
        for (Participant participant : list_members) {
            names.add(participant.name);
        }
        new MaterialDialog.Builder(getContext())
                .title("Select participants")
                .items(names)
                .itemsCallbackMultiChoice(
                        null,
                        new MaterialDialog.ListCallbackMultiChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                                ids.clear();
                                for (int i = 0; i < which.length; i++) {
                                    ids.add(which[i]);
                                }
                                return true;
                            }
                        })
                .positiveText("Ok")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        list_participants.clear();
                        for (Integer id : ids) list_participants.add(names.get(id));
                        list_ids = ids;
                        dialog.dismiss();
                        setHashTagData();
                    }
                })
                .negativeText("Add")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        final MaterialDialog parent = dialog;
                        new MaterialDialog.Builder(getContext())
                                .content("Input trip organizer")
                                .inputType(InputType.TYPE_CLASS_TEXT)
                                .input("", "", new MaterialDialog.InputCallback() {
                                    @Override
                                    public void onInput(MaterialDialog dialog, CharSequence input) {
                                        if  (input.toString().trim().length()==0) return;
                                        Participant participant = new Participant();
                                        participant.name = input.toString();
                                        handler.addParticipant(participant);
                                        dialog.dismiss();
                                        parent.dismiss();
                                        setSpinner();
                                        onSelectParticipants();
                                    }
                                }).show();
                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        for (Integer id : ids) {
                            handler.deleteParticipant(list_members.get(id).id);
                            list_members.remove(id);
                        }
                        dialog.dismiss();
                        setSpinner();
                        onSelectParticipants();
                    }
                })
                .alwaysCallMultiChoiceCallback()
                .autoDismiss(false)
                .neutralText("Delete")
                .show();
    }

    private void onClickCompleteTrip() {
        if (location.isEmpty()) {
            Toast.makeText(getContext(), "Please select location", Toast.LENGTH_SHORT).show();
            return;
        }
        if (text_start_date.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please input start date", Toast.LENGTH_SHORT).show();
            return;
        }
        if (text_end_date.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please input end date", Toast.LENGTH_SHORT).show();
            return;
        }
        if (list_members.size() == 0) {
            Toast.makeText(getContext(), "Please select Trip Organizer", Toast.LENGTH_SHORT).show();
            return;
        }
        String participant_ids = "";
        for (Integer id : list_ids) {
            if (!participant_ids.equals("")) participant_ids = participant_ids + ",";
            participant_ids = participant_ids + id;
        }
        String hike_ids = "";
        for (Hike hike : list_hikes) {
            int id = handler.addHike(hike);
            if (id == -1) {
                Toast.makeText(getContext(), "Database error", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!hike_ids.equals("")) hike_ids = hike_ids + ",";
            hike_ids = hike_ids + id;
        }

        trip.status = 1;
        trip.location = location;
        trip.startDate = text_start_date.getText().toString();
        trip.endDate = text_end_date.getText().toString();
        trip.tripOrganizer = list_members.get(spinner_trip_organizer.getSelectedItemPosition()).id;
        trip.participants = participant_ids;
        trip.noOfDays = edit_number_of_days.getText().toString();
        trip.accommodations = edit_accommodations.getText().toString();
        trip.hikes = hike_ids;
        trip.reminders = edit_reminders.getText().toString();
        trip.wildlifeSeen = edit_wildlife_seen.getText().toString();
        trip.highlights = edit_highlights.getText().toString();

        int id = handler.updateTrip(trip);
        if (id == 0) {
            Toast.makeText(getContext(), "Database error", Toast.LENGTH_SHORT).show();
        } else {
            if (mListener != null) mListener.onClickCompleteFutureTripButton();
        }
    }

    private void onClickUpdateTrip() {
        if (location.isEmpty()) {
            Toast.makeText(getContext(), "Please select location", Toast.LENGTH_SHORT).show();
            return;
        }
        if (text_start_date.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please input start date", Toast.LENGTH_SHORT).show();
            return;
        }
        if (list_members.size() == 0) {
            Toast.makeText(getContext(), "Please select Trip Organizer", Toast.LENGTH_SHORT).show();
            return;
        }
        String participant_ids = "";
        for (Integer id : list_ids) {
            if (!participant_ids.equals("")) participant_ids = participant_ids + ",";
            participant_ids = participant_ids + id;
        }
        String hike_ids = "";
        for (Hike hike : list_hikes) {
            int id = handler.addHike(hike);
            if (id == -1) {
                Toast.makeText(getContext(), "Database error", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!hike_ids.equals("")) hike_ids = hike_ids + ",";
            hike_ids = hike_ids + id;
        }

        trip.location = location;
        trip.startDate = text_start_date.getText().toString();
        trip.endDate = text_end_date.getText().toString();
        trip.tripOrganizer = list_members.get(spinner_trip_organizer.getSelectedItemPosition()).id;
        trip.participants = participant_ids;
        trip.noOfDays = edit_number_of_days.getText().toString();
        trip.accommodations = edit_accommodations.getText().toString();
        trip.hikes = hike_ids;
        trip.reminders = edit_reminders.getText().toString();
        trip.wildlifeSeen = edit_wildlife_seen.getText().toString();
        trip.highlights = edit_highlights.getText().toString();

        int id = handler.updateTrip(trip);
        if (id == 0) {
            Toast.makeText(getContext(), "Database error", Toast.LENGTH_SHORT).show();
        } else {
            if (mListener != null) mListener.onClickUpdateFutureTripButton();
        }
    }

    private void setSpinner() {
        list_members = handler.getAllParticipant();
        List<String> names = new ArrayList<>();
        for (Participant participant : list_members) {
            names.add(participant.name);
        }
        String[] array = new String[list_members.size()];
        names.toArray(array);
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_trip_organizer.setAdapter(adapter);
        for (int i = 0; i < list_members.size(); i++) {
            if (list_members.get(i).id == trip.tripOrganizer) {
                spinner_trip_organizer.setSelection(i);
                break;
            }
        }
    }

    private void setHashTagData() {
        hashtags.setData(list_participants, new HashtagView.DataTransform<String>() {
            @Override
            public CharSequence prepare(String item) {
                SpannableString spannableString = new SpannableString(item);
                return spannableString;
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
    }

    @Override
    public void onEditTextChanged(int position, int index, String s) {
        switch (index) {
            case 0:
                list_hikes.get(position).hikeName = s;
                break;
            case 1:
                if (s.length() > 0) list_hikes.get(position).noOfDayHikes = Integer.parseInt(s);
                else list_hikes.get(position).noOfDayHikes = 0;
                break;
            case 2:
                if (s.length() > 0) list_hikes.get(position).noOfBagNights = Integer.parseInt(s);
                else list_hikes.get(position).noOfBagNights = 0;
                break;
            case 3:
                if (s.length() > 0) list_hikes.get(position).distance = Float.parseFloat(s);
                else list_hikes.get(position).distance = 0;
                break;
            case 4:
                list_hikes.get(position).contactInfo = s;
                break;
            case 5:
                list_hikes.get(position).dailyBreakdown = s;
                break;
        }
    }

    @Override
    public void onUnitSwitchChanged(int position, boolean isChecked) {
        if (isChecked) list_hikes.get(position).unit = 1;
        else list_hikes.get(position).unit = 0;
    }

    public interface EditFutureTripListener {
        void onClickUpdateFutureTripButton();
        void onClickCompleteFutureTripButton();
    }
}

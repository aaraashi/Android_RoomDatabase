package com.example.hyongdonjeong_juheekim_comp304lab4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {
    private List<Patient> patients = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_list, parent, false);
        return new PatientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
        Patient currentPatient = patients.get(position);
        holder.textViewPatientId.setText(String.valueOf(currentPatient.getPatientId()));
        holder.textViewFirstName.setText(currentPatient.getFirstname());
        holder.textViewLastName.setText(currentPatient.getLastname());
        holder.textViewDepartment.setText(currentPatient.getDepartment());
        holder.textViewNurseId.setText(String.valueOf(currentPatient.getNurseId()));
        holder.textViewRoom.setText(currentPatient.getRoom());

    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
        notifyDataSetChanged();
    }

    public Patient getPatientsAt(int position) {
        return patients.get(position);
    }

    class PatientHolder extends RecyclerView.ViewHolder {
        private TextView textViewPatientId;
        private TextView textViewFirstName;
        private TextView textViewLastName;
        private TextView textViewDepartment;
        private TextView textViewNurseId;
        private TextView textViewRoom;

        public PatientHolder(View itemView) {
            super(itemView);
            textViewPatientId = itemView.findViewById(R.id.textView_p_patientId);
            textViewFirstName = itemView.findViewById(R.id.textView_p_fName);
            textViewLastName = itemView.findViewById(R.id.textView_p_lName);
            textViewDepartment = itemView.findViewById(R.id.textView_p_dept);
            textViewNurseId = itemView.findViewById(R.id.textView_p_nurseId);
            textViewRoom = itemView.findViewById(R.id.textView_p_room);

            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(patients.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Patient patient);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
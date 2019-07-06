package com.example.hyongdonjeong_juheekim_comp304lab4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {
    private List<Test> tests = new ArrayList<>();
    private TestAdapter.OnItemClickListener listener;

    @NonNull
    @Override
    public TestAdapter.TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_list, parent, false);
        return new TestAdapter.TestHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.TestHolder holder, int position) {
        Test currentTest = tests.get(position);
        holder.textViewTestId.setText("TestId: " + String.valueOf(currentTest.getTestId()));
        holder.textViewPatientId.setText("PatientId: " + String.valueOf(currentTest.getPatientId()));
        holder.textViewNurseId.setText("NurseId: " + String.valueOf(currentTest.getNurseId()));
        holder.textViewBPL.setText("BPL: " + String.valueOf(currentTest.getBPL()));
        holder.textViewBPH.setText("BPH: " + String.valueOf(currentTest.getBPH()));
        holder.textViewTemp.setText("Temp.: " + String.valueOf(currentTest.getTemperature()));
        holder.textViewWeight.setText("Weight: " + String.valueOf(currentTest.getWeight()));
        holder.textViewHeight.setText("Height: " + String.valueOf(currentTest.getHeight()));
        holder.textViewESL.setText("ESL: " + String.valueOf(currentTest.getESL()));
        holder.textViewESR.setText("ESR: " + String.valueOf(currentTest.getESR()));
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
        notifyDataSetChanged();
    }

    class TestHolder extends RecyclerView.ViewHolder {
        private TextView textViewTestId;
        private TextView textViewPatientId;
        private TextView textViewNurseId;
        private TextView textViewBPL;
        private TextView textViewBPH;
        private TextView textViewTemp;
        private TextView textViewWeight;
        private TextView textViewHeight;
        private TextView textViewESL;
        private TextView textViewESR;

        public TestHolder(View itemView) {
            super(itemView);
            textViewTestId = itemView.findViewById(R.id.textView_t_testId);
            textViewPatientId = itemView.findViewById(R.id.textView_t_patientId);
            textViewNurseId = itemView.findViewById(R.id.textView_t_nurseId);
            textViewBPL = itemView.findViewById(R.id.textView_t_BPL);
            textViewBPH = itemView.findViewById(R.id.textView_t_BPH);
            textViewTemp = itemView.findViewById(R.id.textView_t_temp);
            textViewWeight = itemView.findViewById(R.id.textView_t_weight);
            textViewHeight = itemView.findViewById(R.id.textView_t_height);
            textViewESL = itemView.findViewById(R.id.textView_t_ESL);
            textViewESR = itemView.findViewById(R.id.textView_t_ESR);

            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(tests.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Test Test);
    }

    public void setOnItemClickListener(TestAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

}

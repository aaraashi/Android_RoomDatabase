package com.example.hyongdonjeong_juheekim_comp304lab4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {
    private List<Test> tests = new ArrayList<>();
    private List<Test> testFull;
    private TestAdapter.OnItemClickListener listener;

    @NonNull
    @Override
    public TestAdapter.TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_list, parent, false);
        return new TestAdapter.TestHolder(itemView);
    }

//    TestAdapter(List<Test> tests) {
//        this.tests = tests;
//        testFull = new ArrayList<>(tests);
//    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.TestHolder holder, int position) {
        Test currentTest = tests.get(position);
        holder.textViewTestId.setText("TestId: " + (currentTest.getTestId()));
        holder.textViewPatientId.setText("PatientId: " + (currentTest.getPatientId()));
        holder.textViewNurseId.setText("NurseId: " + (currentTest.getNurseId()));
        holder.textViewBPL.setText("BPL: " + (currentTest.getBPL()));
        holder.textViewBPH.setText("BPH: " + (currentTest.getBPH()));
        holder.textViewTemp.setText("Temp.: " + (currentTest.getTemperature()));
        holder.textViewWeight.setText("Weight: " + (currentTest.getWeight()));
        holder.textViewHeight.setText("Height: " + (currentTest.getHeight()));
        holder.textViewESL.setText("ESL: " + (currentTest.getESL()));
        holder.textViewESR.setText("ESR: " + (currentTest.getESR()));
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
        notifyDataSetChanged();
    }

    public Test getTestsAt(int position) {
        return tests.get(position);
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

    public Filter getFilter() {
        return testFilter;
    }

    private Filter testFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Test> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(testFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Test item : testFull) {
                    if (String.valueOf(item.getPatientId()).contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            tests.clear();
            tests.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public interface OnItemClickListener {
        void onItemClick(Test Test);
    }

    public void setOnItemClickListener(TestAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

}

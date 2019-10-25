package mobile.divulga.editais.ifsuldeminas.edu.br.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.listeners.CategoryClickListener;
import mobile.divulga.editais.ifsuldeminas.edu.br.listeners.NoticeClickListener;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Category;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Notice;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.NoticesCategory;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Results;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Utils;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.RequestMethods;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.ResultCallback;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.WebService;

public class CategoriesFragment extends Fragment {

    private static final String USER_ID = "userId";
    private OnFragmentInteractionListener mListener;
    private LinearLayout layout, preLayout;
    private String currentTag = Utils.getTagScreenAllNotices();
    private List<Category> categories;
    private ArrayList<String> insertedBy = new ArrayList<>();

    public static CategoriesFragment newUserCategoriesFragmentInstance(int userId, String currentTag) {
        Bundle args = new Bundle();
        args.putInt(USER_ID, userId);
        args.putString(Utils.getCurrentTagKey(), currentTag);

        CategoriesFragment fragment = new CategoriesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int userId = 0;
        if (getArguments() != null) {
            userId = getArguments().getInt(USER_ID);
            currentTag = getArguments().getString(Utils.getCurrentTagKey());
        }
        getCategories((Activity) getContext(), userId);
    }

    private void getCategories(final Context context, int userId){

        String endpoint = "";

        if(Utils.isTagScreenAllCategories(currentTag)){
            endpoint = "category/listNotSubscribed/"+userId;
        } else {
            endpoint = "category/listSubscribed/"+userId;
        }

        new WebService<Category[]>(Category[].class, context).queryList(endpoint, null, RequestMethods.GET, new ResultCallback<Category[]>() {
            @Override
            public void onSuccess(Category[] categoriesArray) {
                if (categoriesArray != null) {
                    categories = Arrays.asList(categoriesArray);
                    fillScreenTable(categories, getContext());
                }
            }

            @Override
            public void onError(Exception e) {
                String error = String.format("Erro desconhecido: %s", e.getMessage());
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVolleyError(VolleyError e) {
                Toast.makeText(context, "Ocorreu um erro ao realizar a operação!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_categories, container, false);

        layout = v.findViewById(R.id.layoutCategory);
        preLayout = v.findViewById(R.id.layoutCategoryData);

        return v;
    }

    private int getColor(int index, Context context){
        if(index%2==0) {
            return ContextCompat.getColor(context, R.color.cardview_light_background);
        } else {
            return ContextCompat.getColor(context, R.color.cardview_dark_background);
        }
    }

    private int getBackground(int index){
        if(index%2==0) {
            return R.drawable.bg_circle;
        } else {
            return R.drawable.bg_light;
        }
    }

    private TextView getTextView(int index, String textContent, Context context){
        TextView textView = new TextView(context);

        textView.setText(textContent);
        textView.setTextSize(20);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        textView.setPadding(5,5,5,5);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        textView.setTextColor(getColor(index, context));

        return textView;
    }

    public void fillScreenTable(List<Category> categories, Context context){
        if(preLayout !=null) {
            if (preLayout.getChildCount() > 0)
                preLayout.removeAllViews();
        }
        for (int i = 0; i < categories.size(); i++) {

            Category category = categories.get(i);

            TextView description = getTextView(i, category.getDescription(), context);
            Object[] tags = new Object[2];
            tags[0] = category;
            tags[1] = currentTag;
            LinearLayout contentLayout = getLayout(i, tags, context);

            contentLayout.addView(description);

            preLayout.addView(contentLayout);
        }
    }

    private LinearLayout getLayout(int index, Object[] tagContents, Context context){
        LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.setOnClickListener(new CategoryClickListener());
        layout.setBackgroundResource(getBackground(index));
        layout.setTag(tagContents);

        return layout;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
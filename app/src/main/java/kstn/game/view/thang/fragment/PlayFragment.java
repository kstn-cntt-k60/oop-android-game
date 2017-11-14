package kstn.game.view.thang.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import kstn.game.MainActivity;
import kstn.game.R;
import kstn.game.view.state.ViewStateManager;
import kstn.game.view.thang.data.QuestionManagerDAO;
import kstn.game.logic.model.CauHoiModel;

public class PlayFragment extends Fragment {
    private ViewStateManager stateManager;

    private TextView txtMang;
    private TextView txtMoney;
    private TextView txtLevel;
    private CauHoiModel cauhoi;
    private TextView txtCauHoi;
    private MediaPlayer song;
    private TextView txtNoiDungKim;

    // bien cờ
    private boolean[] flag = new boolean[26];
    private int dem=0;
    private int len;
    // list o bi mat
    ArrayList<TextView> dataCell = new ArrayList<>();

    // quan li cau hoi, ket noi sqlite
    private QuestionManagerDAO questionManagerDAO;
    private ArrayList<CauHoiModel> dataCauHoi;
    ArrayList<Button> dataLetter = new ArrayList<Button>();
    ArrayList<Button> dataDoan = new ArrayList<Button>();
    ArrayList<Integer> ArrayNumber = new ArrayList<>();
    private boolean[] isOpen;
    ArrayList<String> data = new ArrayList<>();
    private Character[] data_copy;
    int height;
    View view1,view2;
    Button   btnDoanX,btnBackSpace,btnSpace,btnHuy;
    TextView txtTraLoi;
    Dialog hopthoai1,hopthoai2;
    Button btnDoan;
    MediaPlayer  songFail;
    MediaPlayer  songTingTing;

    public PlayFragment() {
    }

    public void setStateManager(ViewStateManager stateManager) { this.stateManager = stateManager; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_play, container, false);
        result.setBackgroundColor(Color.parseColor("#00000000"));
        result.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        return result;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // khoi tao bien cờ
        for (int i = 0; i < 26; i++) {
            flag[i] = true;
        }
        // get chiều dài rộng mà hình
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
         height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        // ánh xạ các biến
        txtMang = (TextView) view.findViewById(R.id.txtMang);
        txtMoney = (TextView) view.findViewById(R.id.txtMoney);
        txtLevel = (TextView) view.findViewById(R.id.txtLevel);
        txtCauHoi = (TextView) view.findViewById(R.id.txtCauHoi);
        txtNoiDungKim = (TextView) view.findViewById(R.id.txtNoiDungKim);
        songFail = MediaPlayer.create(getActivity(), R.raw.failure);
        songTingTing = MediaPlayer.create(getActivity(), R.raw.tingting);

        // khởi tạo arraylist Điểm vòng quay
        data.add("800");
        data.add("Mất điểm");
        data.add("100");
        data.add("200");
        data.add("Nhân 2");
        data.add("300");
        data.add("400");
        data.add("May Mắn");
        data.add("300");
        data.add("700");
        data.add("Mất Lượt");
        data.add("600");
        data.add("Chia 2");
        data.add("500");
        data.add("100");
        data.add("Thêm Lượt");
        data.add("200");
        data.add("300");
        data.add("Thưởng");
        data.add("900");

       // khởi tạo ds các ô bí mật
        dataCell.add((TextView) view.findViewById(R.id.txt0));
        dataCell.add((TextView) view.findViewById(R.id.txt1));
        dataCell.add((TextView) view.findViewById(R.id.txt2));
        dataCell.add((TextView) view.findViewById(R.id.txt3));
        dataCell.add((TextView) view.findViewById(R.id.txt4));
        dataCell.add((TextView) view.findViewById(R.id.txt5));
        dataCell.add((TextView) view.findViewById(R.id.txt6));
        dataCell.add((TextView) view.findViewById(R.id.txt7));
        dataCell.add((TextView) view.findViewById(R.id.txt8));
        dataCell.add((TextView) view.findViewById(R.id.txt9));
        dataCell.add((TextView) view.findViewById(R.id.txt10));
        dataCell.add((TextView) view.findViewById(R.id.txt11));
        dataCell.add((TextView) view.findViewById(R.id.txt12));
        dataCell.add((TextView) view.findViewById(R.id.txt13));
        dataCell.add((TextView) view.findViewById(R.id.txt14));
        dataCell.add((TextView) view.findViewById(R.id.txt15));
        dataCell.add((TextView) view.findViewById(R.id.txt16));
        dataCell.add((TextView) view.findViewById(R.id.txt17));
        dataCell.add((TextView) view.findViewById(R.id.txt18));
        dataCell.add((TextView) view.findViewById(R.id.txt19));
        dataCell.add((TextView) view.findViewById(R.id.txt20));
        dataCell.add((TextView) view.findViewById(R.id.txt21));
        dataCell.add((TextView) view.findViewById(R.id.txt22));
        dataCell.add((TextView) view.findViewById(R.id.txt23));
        dataCell.add((TextView) view.findViewById(R.id.txt24));
        dataCell.add((TextView) view.findViewById(R.id.txt25));
        dataCell.add((TextView) view.findViewById(R.id.txt26));

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        view1 = inflater.inflate(R.layout.giaodien_alert_doan,null);
        view2 = inflater.inflate(R.layout.giaodien_alert_letter,null);
        // anh xa o letter
        dataLetter.add((Button) view2.findViewById(R.id.btnA));
        dataLetter.add((Button) view2.findViewById(R.id.btnB));
        dataLetter.add((Button) view2.findViewById(R.id.btnC));
        dataLetter.add((Button) view2.findViewById(R.id.btnD));
        dataLetter.add((Button) view2.findViewById(R.id.btnE));
        dataLetter.add((Button) view2.findViewById(R.id.btnF));
        dataLetter.add((Button) view2.findViewById(R.id.btnG));
        dataLetter.add((Button) view2.findViewById(R.id.btnH));
        dataLetter.add((Button) view2.findViewById(R.id.btnI));
        dataLetter.add((Button) view2.findViewById(R.id.btnJ));
        dataLetter.add((Button) view2.findViewById(R.id.btnK));
        dataLetter.add((Button) view2.findViewById(R.id.btnL));
        dataLetter.add((Button) view2.findViewById(R.id.btnM));
        dataLetter.add((Button) view2.findViewById(R.id.btnN));
        dataLetter.add((Button) view2.findViewById(R.id.btnO));
        dataLetter.add((Button) view2.findViewById(R.id.btnP));
        dataLetter.add((Button) view2.findViewById(R.id.btnQ));
        dataLetter.add((Button) view2.findViewById(R.id.btnR));
        dataLetter.add((Button) view2.findViewById(R.id.btnS));
        dataLetter.add((Button) view2.findViewById(R.id.btnT));
        dataLetter.add((Button) view2.findViewById(R.id.btnU));
        dataLetter.add((Button) view2.findViewById(R.id.btnV));
        dataLetter.add((Button) view2.findViewById(R.id.btnW));
        dataLetter.add((Button) view2.findViewById(R.id.btnX));
        dataLetter.add((Button) view2.findViewById(R.id.btnY));
        dataLetter.add((Button) view2.findViewById(R.id.btnZ));


        btnHuy =(Button) view1.findViewById(R.id.btnHuy);
        btnDoanX = (Button) view1.findViewById(R.id.btnDoanX);
        dataDoan.add((Button) view1.findViewById(R.id.btnQ));
        dataDoan.add((Button) view1.findViewById(R.id.btnW));
        dataDoan.add((Button) view1.findViewById(R.id.btnE));
        dataDoan.add( (Button) view1.findViewById(R.id.btnR));
        dataDoan.add( (Button) view1.findViewById(R.id.btnT));
        dataDoan.add( (Button) view1.findViewById(R.id.btnY));
        dataDoan.add( (Button) view1.findViewById(R.id.btnU));
        dataDoan.add( (Button) view1.findViewById(R.id.btnI));
        dataDoan.add( (Button) view1.findViewById(R.id.btnO));
        dataDoan.add( (Button) view1.findViewById(R.id.btnP));
        dataDoan.add( (Button) view1.findViewById(R.id.btnA));
        dataDoan.add( (Button) view1.findViewById(R.id.btnS));
        dataDoan.add((Button) view1.findViewById(R.id.btnD));
        dataDoan.add( (Button) view1.findViewById(R.id.btnF));
        dataDoan.add((Button) view1.findViewById(R.id.btnG));
        dataDoan.add( (Button) view1.findViewById(R.id.btnH));
        dataDoan.add( (Button) view1.findViewById(R.id.btnJ));
        dataDoan.add((Button) view1.findViewById(R.id.btnK));
        dataDoan.add( (Button) view1.findViewById(R.id.btnL));
        dataDoan.add((Button) view1.findViewById(R.id.btnZ));
        dataDoan.add( (Button) view1.findViewById(R.id.btnX));
        dataDoan.add( (Button) view1.findViewById(R.id.btnC));
        dataDoan.add( (Button) view1.findViewById(R.id.btnV));
        dataDoan.add( (Button) view1.findViewById(R.id.btnB));
        dataDoan.add( (Button) view1.findViewById(R.id.btnN));
        dataDoan.add( (Button) view1.findViewById(R.id.btnM));
        txtTraLoi = (TextView) view1.findViewById(R.id.txtTraLoi);
        btnBackSpace = (Button) view1.findViewById(R.id.btnBackSpace);
        btnSpace = (Button) view1.findViewById(R.id.btnSpace);

        // khởi tạo trình quản lí câu hỏi SQL lite
        questionManagerDAO = new QuestionManagerDAO(getActivity());
        questionManagerDAO.open();
        dataCauHoi = questionManagerDAO.getData();
        questionManagerDAO.close();
        for(int i=0;i<dataCauHoi.size();i++){
            ArrayNumber.add(i);

        }
        hopthoai1 = new Dialog(getActivity(),R.style.Theme_Dialog);
        hopthoai2 = new Dialog(getActivity(),R.style.Theme_Dialog);
        this.InitAlert(hopthoai2,view2);
        hopthoai2.setCancelable(false);
        hopthoai2.setCanceledOnTouchOutside(false);
        this.InitAlert(hopthoai1,view1);

       this.UpdateContext();
        if(Integer.parseInt(txtMang.getText().toString())>0&&(dem<len)) {
            //imgNon.setOnTouchListener(this);
        } else{
            if(dem==len){
                Toast.makeText(getActivity(),"Nhấn button để Đoán Luôn",Toast.LENGTH_LONG);
            }
           // imgNon.setOnTouchListener(null);
        }

        btnDoan = (Button) view.findViewById(R.id.btnDoan);
        btnDoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hopthoai1.show();
                txtTraLoi.setText("");
                for(final Button btn:dataDoan) {
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            txtTraLoi.setText(txtTraLoi.getText().toString() + btn.getText().toString());
                        }
                    });
                }
                btnSpace.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtTraLoi.setText(txtTraLoi.getText().toString()+" ");
                    }
                });
                btnBackSpace.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        if(txtTraLoi.getText().length()>0) {
                            txtTraLoi.setText(txtTraLoi.getText().subSequence(0, txtTraLoi.getText().length() - 1));
                        }
                    }
            });

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hopthoai1.dismiss();
                    }
                });
                btnDoanX.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(txtTraLoi.getText().toString().equals(cauhoi.getCauTraLoi())){
                            MediaPlayer  song1 = MediaPlayer.create(getActivity(), R.raw.tingting);
                            song1.start();
                            UpdateContext();
                        }
                        else{
                            songFail.start();
                            if(Integer.parseInt(txtMoney.getText().toString())>1000){
                                txtMoney.setText((Integer.parseInt(txtMoney.getText().toString())-1000)+"");
                            }
                            else if(Integer.parseInt(txtMang.getText().toString())>0){
                                txtMang.setText((Integer.parseInt(txtMang.getText().toString())-1)+"");
                            }else {
                                Toast.makeText(getActivity(),"game Over",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });
    }

    public  void InitAlert(Dialog hopthoai, View view){
        hopthoai.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        hopthoai.setContentView(view);
        WindowManager.LayoutParams w = hopthoai.getWindow().getAttributes();
        w.gravity = Gravity.BOTTOM;
        w.x=0;
        w.height=(int)(height*0.53);
        hopthoai.getWindow().setAttributes(w);
    }

    public void UpdateContext() {
        for(int i=0;i< dataLetter.size();i++){
            dataLetter.get(i).setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.button_mini));

        }
        hopthoai1.dismiss();
        //imgNon.setEnabled(true);
        isOpen = new boolean[27];
        data_copy = new Character[27];
        dem=0;
        txtMang.setText("3");
        for (int i = 0; i < 26; i++) {
            flag[i] = true;
        }
        for (int i = 0; i < 27; i++) {
            dataCell.get(i).setBackgroundResource(R.drawable.button_mini);
            dataCell.get(i).setText("");
            isOpen[i] = false;
        }

        Collections.shuffle(ArrayNumber);
        cauhoi = dataCauHoi.get(ArrayNumber.get(0));
        ArrayNumber.remove(0);

        txtCauHoi.setText(cauhoi.getCauhoi());
        ArrayList<String> c = ToArray(cauhoi);

        ArrayList<Integer> tmp = new ArrayList<>();
        len =0;
        for (int i = 0; i < c.size(); i++) {
            len+=c.get(i).length();

            for (int j = 0; j < c.get(i).length(); j++) {

                tmp.add(i * 9 + (9 - c.get(i).length()) / 2 + j);
                data_copy[i * 9 + (9 - c.get(i).length()) / 2 + j] = c.get(i).charAt(j);
            }

        }
        for (int i = 0; i < 27; i++) {
            if (!tmp.contains(i)) {
                dataCell.get(i).setBackgroundColor(Color.GRAY);
                data_copy[i] = '0';
            }
        }
    }


    public ArrayList<String> ToArray(CauHoiModel cauhoi) {
        String[] str = cauhoi.getCauTraLoi().split(" ");
        int len = 0;
        for (int i = 0; i < str.length; i++) len += str[i].length();
        ArrayList<String> c = new ArrayList<>();
        int start = 0;
        int size = 0;
        while (size != len) {
            String buff = new String();
            int dem = 0;
            for (int i = start; i < str.length; i++) {

                if (dem + str[i].length() <= 9) {
                    buff += str[i];
                    dem += str[i].length();
                } else {
                    start = i;
                    break;
                }
            }

            if (!buff.isEmpty()) {
                c.add(buff);
                size += buff.length();
            }
        }
        return c;
    }
}



package com.example.alfred.utils;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputEditText;

public abstract class Mascara {

    public static TextWatcher insert(final String mascara, final TextInputEditText et) {
        return new TextWatcher() {
            boolean estaAtualizando;
            String textAntigo = "";

            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                String str = Mascara.removeMascara(s.toString());
                String mascaraAtual = "";
                if (estaAtualizando) {
                    textAntigo = str;
                    estaAtualizando = false;
                    return;
                }
                int i = 0;
                for (char m : mascara.toCharArray()) {
                    if (m != '#' && str.length() > textAntigo.length()) {
                        mascaraAtual += m;
                        continue;
                    }
                    try {
                        mascaraAtual += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                estaAtualizando = true;
                et.setText(mascaraAtual);
                et.setSelection(mascaraAtual.length());
            }

            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

    private static String removeMascara(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "");
    }
}

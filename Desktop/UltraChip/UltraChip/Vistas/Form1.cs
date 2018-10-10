using System;
using System.Collections.Generic;
using System.Windows.Forms;
using UltraChip.Presentador;

namespace UltraChip
{
    public partial class Form1 : Form
    {
        private List<String> lista;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            lista = CarrierDAO.getInstance().companias();
            String cadena = "";
            foreach (String elemento in lista)
                cadena += elemento + " ";

            MessageBox.Show(cadena);
        }

        private void btCargar_Click(object sender, EventArgs e)
        {
            label1.Text = lista.Count.ToString();
        }
    }
}

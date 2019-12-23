interface IResim
{
    void Goster(PictureBox pb, string Dizin);
}
 
class Resim : IResim
{
    public void Goster(PictureBox pb, string Dizin)
    {
        pb.ImageLocation = Dizin;
    }
}
 
class ProxyResim : IResim
{
    Resim resim;
    bool ResimYuklendi;
    PictureBox pb;
    string Dizin;
    void ResimYukle(object o)
    {
        resim = new Resim();
        resim.Goster(pb, Dizin);
        ResimYuklendi = true;
    }
    public void Goster(PictureBox pb, string Dizin)
    {
        this.pb = pb;
        this.Dizin = Dizin;
 
        if (resim == null)
        {
            new System.Threading.Timer(new TimerCallback(ResimYukle), this, 2000, 0);
        }
 
        if (!ResimYuklendi)
        {
            pb.ImageLocation = "D:\\Yukleniyor.gif";
        }
    }
}


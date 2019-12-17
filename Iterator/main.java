static void Main(string[] args)
{
    DateTimeAggregate tarih = new DateTimeAggregate();
    tarih.startDate = new DateTime(2017, 01, 01);
    tarih.endDate = DateTime.Now;
    IIterator iterator = tarih.CreateIterator();
    while (iterator.HasDate())
    {
        Console.WriteLine(iterator.CurrentDate());
    }
 
    Console.Read();
}
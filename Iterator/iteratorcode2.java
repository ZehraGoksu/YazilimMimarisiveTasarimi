class DateTimeAggregate : IAggregate
{
    public DateTime startDate;
    public DateTime endDate;
    public IIterator CreateIterator() => new DateTimeIterator(this);
}
class DateTimeIterator : IIterator
{
    DateTimeAggregate aggregate;
    DateTime currentDate;
    public DateTimeIterator(DateTimeAggregate aggregate)
    {
        this.aggregate = aggregate;
        currentDate = aggregate.startDate;
    }
    public DateTime CurrentDate() => currentDate;
    public bool HasDate()
    {
        if (currentDate.DayOfWeek == DayOfWeek.Saturday || currentDate.DayOfWeek == DayOfWeek.Sunday)
        {
            int dayCount = currentDate.DayOfWeek == DayOfWeek.Saturday ? 1 : 6;
            currentDate = currentDate.AddDays(dayCount);
        }
        else
        {
            int dayCount = (int)currentDate.DayOfWeek;
            currentDate = currentDate.AddDays(6 - dayCount);
        }
        return currentDate < aggregate.endDate;
    }
}

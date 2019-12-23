interface IAggregate
{
    IIterator CreateIterator();
}
interface IIterator
{
    bool HasDate();
    DateTime CurrentDate();
}

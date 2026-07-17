package chain;

public abstract class BaseOrderHandler implements  OrderHandler {
    protected OrderHandler next;

    @Override
    public void setNext(OrderHandler next){
        this.next = next;
    }

    @Override
    public  boolean handle(OrderRequest request){
        if(!process(request)){
            return false;
        }
        if(next != null){
            return next.handle(request);
        }
        return  true;
    }
    protected  abstract  boolean process(OrderRequest request);
}

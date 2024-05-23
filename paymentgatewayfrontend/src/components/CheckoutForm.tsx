import { PaymentElement } from "@stripe/react-stripe-js"
import "./CheckoutForm.css"
const CheckoutForm = () =>{
    return (
        <div className="main-frame">
            <form className="form">
                <PaymentElement/>
                <button>Submit</button>
            </form>
        </div>
        
    );
};

export default CheckoutForm;
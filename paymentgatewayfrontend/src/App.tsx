
import { Elements } from '@stripe/react-stripe-js';
import './App.css';
import { loadStripe } from '@stripe/stripe-js';
import CheckoutForm from './components/CheckoutForm';
import { useEffect, useState } from 'react';

const stripePromise = loadStripe('pk_test_51PClVJ08yreBK2FRyVRj2iEqnZOfms19frRINZrbr4QkrYqjQIgWAmZbGFG7oCxjQfwDP3cRvLfdKRAdxwCyAsmK00cWxf7Csg');

function App() {
  const [clientState, setClientState]= useState('');
  useEffect(()=>{
    fetch('http://localhost:8080/paymentProcess/checkOut',{
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({amount: 500,token: '', systemUserId: 1, currency: 'USD'}),
    }).then(response => response.json())
    .then(data => setClientState(data.clientSecret))
  },[]);
  const options = {
    clientSecret: 'pi_3PJb2708yreBK2FR086e9Rnf_secret_fh8f13zx02A2Qd4CEDV2AImHQ',
  }
  return (
    <div className="App">
      <Elements stripe={stripePromise} options={options}>
          <CheckoutForm/>
      </Elements>
    </div>
  );
}

export default App;

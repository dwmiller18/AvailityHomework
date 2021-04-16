import './App.css';
import availityLogo from "./assets/availity-logo.png";
import ContactData from "./components/ContactData/ContactData";

function App() {
  return (
    <div className="App">
        <img src={availityLogo} alt="AvailityLogo" className="image" />
      <ContactData />
    </div>
  );
}

export default App;

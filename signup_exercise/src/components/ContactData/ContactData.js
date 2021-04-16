import React, {Component} from 'react';
import classes from './ContactData.module.css';

class ContactData extends Component {
    state = {
        validForm: false
    }

    clickedCancelButton = () => {
        window.confirm("Are You Sure You Wish To Cancel?" +
            "  All Data Will Be Lost.");
    }

    clickedSubmitButton = () => {
        alert("Here we would validate all information.")
    }

    render() {
        return (
            <form>
                <div>
                    <h2>Enter Your Personal Information:</h2>
                    <h5>All Fields Are Required</h5>
                    <div>
                        <input type="text" id='firstName' name='firstName'
                               className={classes.ContactData} placeholder="First Name"/>
                    </div>
                    <div>
                        <input type='text' id='lastName' name='lastName'
                               className={classes.ContactData} placeholder="Last Name"/>
                    </div>
                    <div>
                        <input type='number' id='npi' name='npi'
                               className={classes.ContactData} placeholder="NPI Number"/>
                    </div>
                    <div>
                        <input type='text' id='street' name='street'
                               className={classes.ContactData} placeholder="Street Address"/>
                    </div>
                    <div>
                        <input type='text' id='city' name='city'
                               className={classes.ContactData} placeholder="City"/>
                    </div>
                    <div>
                        <input type='state' id='state' name='state'
                               className={classes.ContactData} placeholder="State"/>
                    </div>
                    <div>
                        <input type='text' inputMode='numeric' className={classes.ContactData}
                               pattern='[0-9]' maxLength='5' minLength='5' placeholder="Zip Code"/>
                    </div>
                    <div>
                        <input type='tel' id='zip' name='zip' className={classes.ContactData} maxLength='10'
                               minLength='10' pattern='[0-9]{3}-[0-9]{2}-[0-9]{3}' placeholder='Phone Number'/>
                    </div>
                    <div>
                        <input type='email' id='email' name='email'
                               className={classes.ContactData} placeholder='Email Address'/>
                    </div>
                    <button className={classes.Button} onClick={this.clickedSubmitButton}>Submit</button>
                    <button className={classes.Button} onClick={this.clickedCancelButton}>Cancel</button>
                </div>
            </form>
        )
    }
}

export default ContactData;
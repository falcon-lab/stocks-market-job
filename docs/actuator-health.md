# stocks-market-job / actuator-health

Stocks Market Job Health

### Path

```
<host>:<port>/actuator/health
<host>:<port>/actuator/info
```

### Methods

  `GET`
  
### Operations

---

#### Get Application Status

```  
GET /actuator/health
```

* **Success Response:**
  * **Code:** `200`
  * **Content:** `{ "status": "UP" }`


---
#### Get Application Additional Information

```
GET /actuator/info
```
  
* **Success Response:**
  * **Code:** `200`
  * **Content:** `{}`


---
### Sample Call
 
```javascript
$.ajax({
  url: "/actuator/health",
  dataType: "json",
  type : "POST",
  success : function(r) {
    console.log(r);
  }
});
```

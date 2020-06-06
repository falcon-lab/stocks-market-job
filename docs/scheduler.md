# stocks-market-job / scheduler

Manage Stocks Jobs Resource

### Path

```
<host>:<port>/scheduler/v1/job
```

### Methods

  `GET` `POST`

### Operations


---

#### GET Active Job Scheduler

```
GET /scheduler/v1/job
```  

* **Success Response:**
  * **Code:** `200`
  * **Content:**
``` 
{
    "data": [{
        "uuid": uuid,
        "startedAt": int,
        "duration": int,
        "state": string,
        "priority": int
      }]
}
```

---

#### POST Start new Scheduler

```
POST /scheduler/v1/job
```

* **Payload:**
  * **data:** `"{ "group": "test", "stock": "test" }"`

* **Success Response:**
  * **Code:** `201`
  
  
---
 
### Sample Call

  ```javascript
    $.ajax({
      url: "/scheduler/v1/job",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```
  